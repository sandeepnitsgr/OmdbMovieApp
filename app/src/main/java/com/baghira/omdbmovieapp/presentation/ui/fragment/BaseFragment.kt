package com.baghira.omdbmovieapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.baghira.omdbmovieapp.di.DaggerMainComponent
import com.baghira.omdbmovieapp.di.MainComponent
import com.baghira.omdbmovieapp.di.ViewModelFactory
import com.baghira.omdbmovieapp.utils.GlobalState
import com.baghira.omdbmovieapp.utils.State
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var component: MainComponent? = null

    abstract fun doViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View

    abstract fun afterViewCreated()
    abstract fun getGlobalStateData(): LiveData<GlobalState>
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Fragment", "Oncreate called")
        super.onCreate(savedInstanceState)
        component?.inject(this) ?: initInjector()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "Oncreateview called")
        return doViewBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViewCreated()
        getGlobalStateData().observe(viewLifecycleOwner) {
            when (it.state) {
                State.SUCCESS -> dismissProgress()
                State.ERROR -> handleError(it.message ?: "Something Went Wrong")
                State.LOADING -> showProgress()
            }
        }
    }

    abstract fun dismissProgress()

    abstract fun showProgress()

    private fun handleError(message: String) {
        dismissProgress()
        showDialog(message)
    }

    private fun showDialog(message: String) {

    }

    private fun initInjector() {
        component = DaggerMainComponent.builder().activityContext(requireContext())
            .build()
        component?.inject(this)
    }
}