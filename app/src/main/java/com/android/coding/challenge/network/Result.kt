package com.android.coding.challenge.network

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val error: String?, val statusCode: Int?) : Result<Nothing>()
}