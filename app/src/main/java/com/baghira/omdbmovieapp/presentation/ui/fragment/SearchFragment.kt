package com.baghira.omdbmovieapp.presentation.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.data.SearchRepository
import com.baghira.omdbmovieapp.data.model.MovieResponse
import com.baghira.omdbmovieapp.databinding.FragmentSearchBinding
import com.baghira.omdbmovieapp.presentation.ui.adapter.MoviesListAdapter
import com.baghira.omdbmovieapp.presentation.viewmodel.SearchViewModel
import com.baghira.omdbmovieapp.presentation.viewmodel.SearchViewModelFactory

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchViewModel: SearchViewModel


    private val movieListAdapter = MoviesListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearchAction()
        initViewModel()
        initObservers()
    }

    private fun initObservers() {
        searchViewModel.searchResultLiveData.observe(
            viewLifecycleOwner,
            { response: ArrayList<MovieResponse?>? ->
                response?.add(null)
                movieListAdapter.setData(response)
            })
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(SearchRepository())
        ).get(SearchViewModel::class.java)

    }

    private fun initSearchAction() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchViewModel.getData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun initRecyclerView() {
        binding.movieListRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = movieListAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    val visibleItemCount = layoutManager!!.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    searchViewModel.checkForRemainingItems(
                        visibleItemCount, totalItemCount,
                        firstVisibleItemPosition
                    )
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}