package com.example.core.usecase.base

sealed class ResultState<out T> {
    data class Success<out T>(val data : T) : ResultState<T>()
    data class Fail(val status: ErrorState, val message: String) : ResultState<Nothing>()
}

enum class ErrorState {
    Fail, Exception, NoInternet, TimeOut
}