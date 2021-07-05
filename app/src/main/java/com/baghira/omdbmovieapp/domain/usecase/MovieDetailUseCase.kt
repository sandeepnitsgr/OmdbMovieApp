package com.baghira.omdbmovieapp.domain.usecase

import com.baghira.omdbmovieapp.common.FavoriteMovieOperation
import com.baghira.omdbmovieapp.data.room.entities.FavoriteMovie
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.domain.model.MovieDetailDTO
import com.baghira.omdbmovieapp.utils.ResponseResult
import com.baghira.omdbmovieapp.utils.getResultData
import com.baghira.omdbmovieapp.utils.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieDetailRepo: Repository<MovieDetailDTO, String>,
    private val favoriteMovieRepository: Repository<List<FavoriteMovie>, FavoriteMovieOperation>
) {

    private val favMap: MutableMap<String, Boolean> by lazy {
        mutableMapOf()
    }


    suspend fun getMovieDetails(imdbId: String): ResponseResult<MovieDetailDTO> {
        return withContext(Dispatchers.IO) {
            val favResponse =
                favoriteMovieRepository.getData(FavoriteMovieOperation.GetAllFavorites)
            if (favResponse.succeeded) {
                favResponse.getResultData().forEach {
                    favMap.clear()
                    favMap[it.imdbID] = true
                }
            }

            val movieDetailsDeffered = async { movieDetailRepo.getData(imdbId) }
            if (favMap.isNullOrEmpty()) {
                val favListResult =
                    favoriteMovieRepository.getData(FavoriteMovieOperation.GetAllFavorites)
                if (favListResult.succeeded) {
                    favMap.apply {
                        clear()
                        favListResult.getResultData().forEach { mov ->
                            favMap[mov.imdbID] = true
                        }
                    }
                }
            }
            val result = movieDetailsDeffered.await()
            if (result.succeeded)
                result.getResultData().apply {
                    if (favMap.containsKey(imdbID))
                        isFavorite = true
                }
            result
        }
    }

    fun clear() {
        favoriteMovieRepository.clear()
        movieDetailRepo.clear()
    }
}