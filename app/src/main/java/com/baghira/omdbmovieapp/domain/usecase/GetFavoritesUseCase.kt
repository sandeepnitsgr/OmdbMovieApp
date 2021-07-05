package com.baghira.omdbmovieapp.domain.usecase

import com.baghira.omdbmovieapp.common.FavoriteMovieOperation
import com.baghira.omdbmovieapp.data.room.entities.FavoriteMovie
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.utils.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoritesUseCase
@Inject constructor(private val favoriteMovieRepository: Repository<List<FavoriteMovie>, FavoriteMovieOperation>) {
    suspend fun getFavoriteMovies(): ResponseResult<List<FavoriteMovie>> {
        return withContext(Dispatchers.IO) {
            favoriteMovieRepository.getData(FavoriteMovieOperation.GetAllFavorites)
        }
    }

    fun clear() {
        favoriteMovieRepository.clear()
    }
}