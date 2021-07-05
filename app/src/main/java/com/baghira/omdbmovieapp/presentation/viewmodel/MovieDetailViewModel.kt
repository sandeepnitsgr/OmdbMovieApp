package com.baghira.omdbmovieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baghira.omdbmovieapp.common.SearchItem
import com.baghira.omdbmovieapp.domain.model.MovieDetailDTO
import com.baghira.omdbmovieapp.domain.usecase.AddRemoveFavoritesUseCase
import com.baghira.omdbmovieapp.domain.usecase.MovieDetailUseCase
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val favoritesUseCase: AddRemoveFavoritesUseCase
) : BaseViewModel() {

    val matchesDetailData: LiveData<MovieDetailDTO>
        get() = _matchesDetailData

    private val _matchesDetailData = MutableLiveData<MovieDetailDTO>()

    fun getMovieDetail(movieId: String) {
        startBackgroundJob({
            movieDetailUseCase.getMovieDetails(movieId)
        }, {
            _matchesDetailData.value = it
        })
    }

    fun handleFavoriteClick() {
        _matchesDetailData.value?.apply {
            isFavorite = !isFavorite
            val fav = isFavorite
            addRemoveFavorite(SearchItem(Poster, Title, Year, imdbID).apply {
                isFavorite = fav
            })
        }
    }

    fun addRemoveFavorite(item: SearchItem) {
        startBackgroundJob({
            favoritesUseCase.getData(item)
        }, {
            //Do something when success
        })
    }
}