package com.baghira.omdbmovieapp.domain.usecase

import com.baghira.omdbmovieapp.common.FavoriteMovieList
import com.baghira.omdbmovieapp.common.FavoriteMovieOperation
import com.baghira.omdbmovieapp.common.SearchItem
import com.baghira.omdbmovieapp.common.SearchQuery
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.domain.model.SearchDomainDTO
import com.baghira.omdbmovieapp.utils.ResponseResult
import com.baghira.omdbmovieapp.utils.getResultData
import com.baghira.omdbmovieapp.utils.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val searchRepository: Repository<SearchDomainDTO, SearchQuery>,
    private val favoriteMovieRepository: Repository<FavoriteMovieList, FavoriteMovieOperation>
) {
    private val favMap: MutableMap<String, Boolean> by lazy {
        mutableMapOf()
    }

    suspend fun getSearchResults(
        searchQuery: String,
        page: Int = 1
    ): ResponseResult<SearchDomainDTO> {
        return withContext(Dispatchers.IO) {
            val favResponse =
                favoriteMovieRepository.getData(FavoriteMovieOperation.GetAllFavorites)
            if (favResponse.succeeded) {
                favResponse.getResultData().forEach {
                    favMap.clear()
                    favMap[it.imdbID] = true
                }
            }
            val deferredSearchList = async {
                searchRepository.getData(requestParams = SearchQuery(searchQuery, page))
            }
            if (favMap.isNullOrEmpty()) {
                val deferredFavList = async {
                    favoriteMovieRepository.getData(FavoriteMovieOperation.GetAllFavorites)
                }
                val favListResult = deferredFavList.await()
                if (favListResult.succeeded) {
                    favMap.apply {
                        clear()
                        favListResult.getResultData().forEach { mov ->
                            favMap[mov.imdbID] = true
                        }
                    }
                }
            }
            val searchResult = deferredSearchList.await()
            if (searchResult.succeeded) {
                updateSearchFav(searchResult.getResultData().searchItems)
            }
            searchResult
        }
    }

    private fun updateSearchFav(searchList: List<SearchItem>) {
        searchList.forEach { searchItem ->
            if (favMap.containsKey(searchItem.imdbID))
                searchItem.isFavorite = true
        }
    }

    fun clear() {
        favoriteMovieRepository.clear()
        searchRepository.clear()
    }
}