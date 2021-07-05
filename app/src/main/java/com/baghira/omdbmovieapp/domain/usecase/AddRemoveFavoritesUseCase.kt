package com.baghira.omdbmovieapp.domain.usecase

import com.baghira.omdbmovieapp.common.FavoriteMovieOperation
import com.baghira.omdbmovieapp.common.SearchItem
import com.baghira.omdbmovieapp.data.room.entities.FavoriteMovie
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.utils.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddRemoveFavoritesUseCase @Inject constructor(
    private val favoriteMovieRepository: Repository<List<FavoriteMovie>, FavoriteMovieOperation>
) {

    suspend fun getData(item: SearchItem): ResponseResult<List<FavoriteMovie>> {
        return withContext(Dispatchers.IO) {
            val operation = if (item.isFavorite) {
                FavoriteMovieOperation.AddFavoriteMovieOp(item)
            } else
                FavoriteMovieOperation.RemoveFavoriteMovieOp(item)
            favoriteMovieRepository.getData(operation)
        }
    }
}
