package com.example.test.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class Result<V> {
    class Success<V>(val value: V) : Result<V>()
    class Failure<V>(val ex: Exception) : Result<V>()
}


abstract class BaseUseCase<Type, in Params> where Type : Any {

    class EmptyParams

    abstract suspend fun run(params: Params): Result<Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Result<Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }
}