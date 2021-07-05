package com.baghira.omdbmovieapp.domain.model

import com.baghira.omdbmovieapp.common.SearchItem

data class SearchDomainDTO(val totalResults: Int, val searchItems: List<SearchItem>)