package com.baghira.omdbmovieapp.data.repositoryimpl

import com.baghira.omdbmovieapp.data.mapToDomain
import com.baghira.omdbmovieapp.data.remote.OMDBApiService
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.domain.model.MovieDetailDTO
import com.baghira.omdbmovieapp.utils.ResponseResult
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(private val movieApi: OMDBApiService) :
    Repository<MovieDetailDTO, String>, BaseRepository<MovieDetailDTO>() {

    override suspend fun getData(requestParams: String): ResponseResult<MovieDetailDTO> {
        return fetchData {
            movieApi.getMovieDetails(requestParams).mapToDomain()
        }
    }

    override fun clear() {

    }

}