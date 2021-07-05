package com.baghira.omdbmovieapp.data.repositoryimpl
import com.baghira.omdbmovieapp.utils.ResponseResult
import kotlinx.coroutines.CancellationException

abstract class BaseRepository<T> {
    suspend fun fetchData(fetch: suspend () -> T): ResponseResult<T> {
        return try {
            ResponseResult.Success(fetch.invoke())
        } catch (throwable: Throwable) {
            if (throwable is CancellationException)
                throw throwable
            else ResponseResult.Error(throwable.localizedMessage ?: "Error occurred", throwable)
        }
    }
}
