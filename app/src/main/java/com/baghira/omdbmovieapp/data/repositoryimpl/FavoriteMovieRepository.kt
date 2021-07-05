package com.baghira.omdbmovieapp.data.repositoryimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.baghira.omdbmovieapp.common.FavoriteMovieList
import com.baghira.omdbmovieapp.common.FavoriteMovieOperation
import com.baghira.omdbmovieapp.data.room.dao.FavoriteMovieDAO
import com.baghira.omdbmovieapp.data.room.entities.FavoriteMovie
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.utils.ResponseResult
import javax.inject.Inject

class FavoriteMovieRepository @Inject constructor(private val favoriteMovieDAO: FavoriteMovieDAO) :
    Repository<FavoriteMovieList, FavoriteMovieOperation>, BaseRepository<FavoriteMovieList>() {

    private val favList: FavoriteMovieList by lazy {
        FavoriteMovieList()
    }
    private val favListUpdateObserver: Observer<List<FavoriteMovie>> by lazy {
        Observer {
            it?.apply {
                favList.clear()
                favList.addAll(this)
            }
        }
    }

    override suspend fun getData(requestParams: FavoriteMovieOperation): ResponseResult<FavoriteMovieList> {
        return fetchData {

            when (requestParams) {
                is FavoriteMovieOperation.AddFavoriteMovieOp -> {
                    val list = FavoriteMovieList()
                    requestParams.data.run {
                        favoriteMovieDAO.insertFavoriteMovie(
                            FavoriteMovie(imdbID, Poster, Title, Year, true)
                        )
                    }
                    list
                }
                is FavoriteMovieOperation.RemoveFavoriteMovieOp -> {
                    val movie = requestParams.data.run {
                        FavoriteMovie(imdbID, Poster, Title, Year, false)
                    }
                    favoriteMovieDAO.removeFromFavorite(movie.imdbID)
                    val list = FavoriteMovieList()
                    list.add(movie)
                    list
                }

                is FavoriteMovieOperation.GetAllFavorites -> {
                    if (favList.isEmpty())
                        favList.addAll(favoriteMovieDAO.getFavourites())
                    favList
                }
            }
        }
    }

    override fun clear() {
    }
}