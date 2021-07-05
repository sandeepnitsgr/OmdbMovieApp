package com.baghira.omdbmovieapp.data.repositoryimpl
import androidx.lifecycle.LiveData
import com.baghira.omdbmovieapp.common.FavoriteMovieOperation
import com.baghira.omdbmovieapp.data.room.dao.FavoriteMovieDAO
import com.baghira.omdbmovieapp.data.room.entities.FavoriteMovie
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.utils.ResponseResult
import javax.inject.Inject

class FavoriteMovieLiveDataRepository @Inject constructor(private val favoriteMovieDAO: FavoriteMovieDAO) :
    Repository<LiveData<List<@JvmSuppressWildcards FavoriteMovie>>, FavoriteMovieOperation.GetAllFavorites>,
    BaseRepository<LiveData<List<FavoriteMovie>>>() {

    override suspend fun getData(requestParams: FavoriteMovieOperation.GetAllFavorites)
            : ResponseResult<LiveData<List<FavoriteMovie>>> {
        return fetchData {
            favoriteMovieDAO.getFavouritesLiveData()
        }
    }

    override fun clear() {

    }
}