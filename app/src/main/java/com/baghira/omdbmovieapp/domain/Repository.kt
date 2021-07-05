package com.baghira.omdbmovieapp.domain

import com.baghira.omdbmovieapp.utils.ResponseResult

interface Repository<T, Params> {
    suspend fun getData(requestParams: Params): ResponseResult<T>

    fun clear()
}