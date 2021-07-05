package com.baghira.omdbmovieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baghira.omdbmovieapp.data.repositoryimpl.SearchRepository
import com.baghira.omdbmovieapp.domain.usecase.SearchMoviesUseCase
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val usecase: SearchMoviesUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(usecase) as T
    }
}