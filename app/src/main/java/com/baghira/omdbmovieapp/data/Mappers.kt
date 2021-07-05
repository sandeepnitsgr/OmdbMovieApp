package com.baghira.omdbmovieapp.data

import com.baghira.omdbmovieapp.data.model.MovieDetailResponse
import com.baghira.omdbmovieapp.data.model.MovieSearchResponse
import com.baghira.omdbmovieapp.domain.model.MovieDetailDTO
import com.baghira.omdbmovieapp.domain.model.SearchDomainDTO

fun MovieSearchResponse.mapToDomain(): SearchDomainDTO {
    return SearchDomainDTO(
        totalResults = totalResults.toInt(),
        searchItems = Search
    )
}

fun MovieDetailResponse.mapToDomain(): MovieDetailDTO {
    return MovieDetailDTO(
        Actors, Awards, Country, Director, Genre, Language, Metascore,
        Plot, Poster, Production, Rated, Released, Response, Runtime, Title,
        Writer, Year, imdbID, imdbRating, imdbVotes
    )
}