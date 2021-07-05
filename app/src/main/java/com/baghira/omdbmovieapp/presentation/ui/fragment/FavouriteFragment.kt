package com.baghira.omdbmovieapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.databinding.FragmentFavouriteBinding
import com.baghira.omdbmovieapp.presentation.ui.adapter.FavouriteMoviesAdapter
import com.baghira.omdbmovieapp.presentation.ui.adapter.MoviesListAdapter
import com.baghira.omdbmovieapp.presentation.viewmodel.FavouriteViewModel
import com.baghira.omdbmovieapp.utils.GlobalState

class FavouriteFragment : BaseFragment() {
    private lateinit var viewmodel: FavouriteViewModel
    private lateinit var binding: FragmentFavouriteBinding
    private val favouriteMoviesAdapter = FavouriteMoviesAdapter()
    override fun doViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        return binding.root
    }

    override fun afterViewCreated() {

    }

    override fun getGlobalStateData(): LiveData<GlobalState> {
        return viewmodel.globalStateData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    override fun dismissProgress() {
    }

    override fun showProgress() {
    }

    private fun initRecyclerView() {
        binding.favouriteMovieRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = favouriteMoviesAdapter
        }
    }

    companion object {
        fun newInstance(): FavouriteFragment {
            return FavouriteFragment()
        }
    }
}