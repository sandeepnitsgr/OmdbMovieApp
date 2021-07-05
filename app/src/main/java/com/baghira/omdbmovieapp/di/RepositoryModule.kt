package com.baghira.omdbmovieapp.di

import androidx.lifecycle.LiveData
import com.baghira.omdbmovieapp.common.FavoriteMovieList
import com.baghira.omdbmovieapp.common.FavoriteMovieOperation
import com.baghira.omdbmovieapp.common.SearchQuery
import com.baghira.omdbmovieapp.data.repositoryimpl.FavoriteMovieLiveDataRepository
import com.baghira.omdbmovieapp.data.repositoryimpl.FavoriteMovieRepository
import com.baghira.omdbmovieapp.data.repositoryimpl.MovieDetailsRepository
import com.baghira.omdbmovieapp.data.repositoryimpl.SearchRepository
import com.baghira.omdbmovieapp.data.room.entities.FavoriteMovie
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.domain.model.MovieDetailDTO
import com.baghira.omdbmovieapp.domain.model.SearchDomainDTO
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSearchRepo(searchRepository: SearchRepository)
            : Repository<SearchDomainDTO, SearchQuery>

    @Binds
    @Singleton
    abstract fun bindSharedPrefRepo(movieDetailsRepository: MovieDetailsRepository)
            : Repository<MovieDetailDTO, String>

    @Binds
    @Singleton
    abstract fun bindFavMovieRepoUpdate(favoriteMovieAddRemoveRepository: FavoriteMovieRepository)
            : Repository<FavoriteMovieList, FavoriteMovieOperation>

    @Binds
    @Singleton
    abstract fun bindFavMovieLiveDataRepoUpdate(favoriteMovieLiveDataRepository: FavoriteMovieLiveDataRepository)
            : Repository<LiveData<List<FavoriteMovie>>, FavoriteMovieOperation.GetAllFavorites>

}