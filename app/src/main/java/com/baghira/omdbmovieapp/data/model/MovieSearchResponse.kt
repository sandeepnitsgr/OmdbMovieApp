package com.baghira.omdbmovieapp.data.model

import com.baghira.omdbmovieapp.common.SearchItem


data class MovieSearchResponse(
    val Response: String,
    val Search: List<SearchItem>,
    val totalResults: String
)