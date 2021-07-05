package com.baghira.omdbmovieapp.presentation.viewmodel.paging

import androidx.paging.DataSource
import com.baghira.omdbmovieapp.common.SearchItem
import com.baghira.omdbmovieapp.presentation.viewmodel.SearchViewModel

class SearchSourceFactory(private val searchViewModel: SearchViewModel) :
    DataSource.Factory<Int, SearchItem>() {
    private var dataSource: SearchPagingSource? = null
    override fun create(): DataSource<Int, SearchItem> {
        dataSource = SearchPagingSource(searchViewModel)
        return dataSource!!
    }

    fun refresh() {
        dataSource?.invalidate()
    }
}