package com.example.test.data

import com.example.test.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Response

suspend fun <T> Call.async(responseHandler: (Response) -> Result<T>): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            responseHandler(this@async.execute())
        } catch (ex: Exception) {
            Result.Failure<T>(ex)
        }
    }
}
