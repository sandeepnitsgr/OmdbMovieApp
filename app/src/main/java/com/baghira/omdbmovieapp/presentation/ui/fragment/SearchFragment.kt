package com.baghira.omdbmovieapp.presentation.ui.fragment

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.databinding.FragmentSearchBinding
import com.baghira.omdbmovieapp.presentation.ui.adapter.SearchAdapter
import com.baghira.omdbmovieapp.presentation.viewmodel.SearchViewModel
import com.baghira.omdbmovieapp.utils.GlobalState

class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding

    private lateinit var searchViewModel: SearchViewModel

    private val movieListAdapter = SearchAdapter()

    override fun dismissProgress() {

    }

    override fun showProgress() {
    }

    private fun initObservers() {
        searchViewModel.searchLoader.observe(
            viewLifecycleOwner,
            { res ->
                if (res) {
                    searchViewModel.pagedSearchList.observe(
                        viewLifecycleOwner, {
                            movieListAdapter.submitList(it)
                        }
                    )
                }
            })
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(SearchViewModel::class.java)

    }

    private fun initSearchAction() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchViewModel.searchMovie(s.toString())
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
        }
    }

    override fun doViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun afterViewCreated() {
        initViewModel()
        initRecyclerView()
        initSearchAction()
        initObservers()
    }

    override fun getGlobalStateData(): LiveData<GlobalState> {
        return searchViewModel.globalStateData
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}