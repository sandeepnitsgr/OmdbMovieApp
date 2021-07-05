package com.baghira.omdbmovieapp.common
data class SearchQuery(
    val query: String,
    var page: Int = 1
)