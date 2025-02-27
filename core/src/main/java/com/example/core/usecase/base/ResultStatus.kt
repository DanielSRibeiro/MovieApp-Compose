package com.example.core.usecase.base

sealed class ResultStatus<out T> {
    data object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Failure(val throwable: Throwable) : ResultStatus<Nothing>()
}