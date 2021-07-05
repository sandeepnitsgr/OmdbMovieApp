package com.baghira.omdbmovieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baghira.omdbmovieapp.data.SearchRepository
import com.baghira.omdbmovieapp.data.model.MovieResponse
import kotlinx.coroutines.*

class SearchViewModel(repository: SearchRepository) : ViewModel(){

    val searchResultLiveData: LiveData<ArrayList<MovieResponse?>?>
    get() = _searchResultLiveData
    private val _searchResultLiveData = MutableLiveData<ArrayList<MovieResponse?>?>()

    fun getData(searchString : String) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)

            val arrayList = ArrayList<MovieResponse?>()
            for(i in searchString.indices) {
                arrayList.add(MovieResponse(i))
            }
            withContext(Dispatchers.Main) {
                _searchResultLiveData.value = arrayList
            }
            delay(5000)
            withContext(Dispatchers.Main) {
                _searchResultLiveData.value = null
            }

        }
    }

    fun checkForRemainingItems(
        visibleItemCount: Int,
        totalItemCount: Int,
        firstVisibleItemPosition: Int
    ) {

    }
}
