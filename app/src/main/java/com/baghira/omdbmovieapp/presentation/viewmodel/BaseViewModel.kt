package com.baghira.omdbmovieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baghira.omdbmovieapp.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    val globalStateData: LiveData<GlobalState>
        get() = _globalStateData
    protected val _globalStateData: MutableLiveData<GlobalState> by lazy {
        MutableLiveData<GlobalState>()
    }

    protected fun executeJob(func: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launchOrError(func, ::handleError)
    }

    protected fun <T> startBackgroundJob(
        backgroundJob: suspend () -> ResponseResult<T>,
        onSuccess: (T) -> Unit,
        onFailure: (ResponseResult.Error) -> Unit = {}
    ): Job {
        return executeJob {
            if (!isLoading()) {
                _globalStateData.value = GlobalState(State.LOADING, "")
            }
            when (val result = backgroundJob.invoke()) {
                is ResponseResult.Success -> {
                    _globalStateData.value = GlobalState(State.SUCCESS)
                    onSuccess(result.getResultData())
                }
                is ResponseResult.Error -> {
                    onFailure(result)
                    handleError(result.throwable ?: Exception("Something went wrong"))
                }
            }
        }
    }


    private fun isLoading() = _globalStateData.value?.state == State.LOADING

    fun handleError(throwable: Throwable) {
        val state = State.ERROR
        _globalStateData.postValue(
            GlobalState(
                state,
                throwable.message ?: "Something went wrong, Please try again"
            )
        )
    }
}