package com.baghira.omdbmovieapp.data.repositoryimpl

import com.baghira.omdbmovieapp.common.SearchQuery
import com.baghira.omdbmovieapp.data.mapToDomain
import com.baghira.omdbmovieapp.data.remote.OMDBApiService
import com.baghira.omdbmovieapp.domain.Repository
import com.baghira.omdbmovieapp.domain.model.SearchDomainDTO
import com.baghira.omdbmovieapp.utils.ResponseResult
import javax.inject.Inject

class SearchRepository @Inject constructor(private val movieApi: OMDBApiService) :
    Repository<SearchDomainDTO, SearchQuery>, BaseRepository<SearchDomainDTO>() {

    override suspend fun getData(requestParams: SearchQuery): ResponseResult<SearchDomainDTO> {
        return fetchData {
            movieApi.getSearchResult(
                requestParams.query,
                requestParams.page
            ).mapToDomain()
        }
    }

    override fun clear() {

    }
}