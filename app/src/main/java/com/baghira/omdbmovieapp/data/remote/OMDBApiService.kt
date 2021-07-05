package com.baghira.omdbmovieapp.data.remote

import com.baghira.omdbmovieapp.data.model.MovieDetailResponse
import com.baghira.omdbmovieapp.data.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBApiService {

    @GET("http://www.omdbapi.com/")
    suspend fun getSearchResult(
        @Query("s") query: String,
        @Query("page") page: Int
    ): MovieSearchResponse


    @GET("http://www.omdbapi.com/")
    suspend fun getMovieDetails(
        @Query("i") movieId: String
    ): MovieDetailResponse
}