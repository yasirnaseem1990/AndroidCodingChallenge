package com.android.coding.challenge.network

import android.util.Log
import com.android.coding.challenge.AppController
import com.android.coding.challenge.util.NetworkUtils
import kotlinx.coroutines.*
import retrofit2.Response




fun <T> request(coroutineScope: CoroutineScope,
                response: suspend() -> Response<T>,
                results:(Result<T>) -> Unit) {
    coroutineScope.launch {
        handlingResponse(
            response,
            { results(it) })
    }
    /*if (NetworkUtils(AppController.ApplicationContext).isConnected()){
        coroutineScope.launch {
            handlingResponse(
                response,
                { results(it) })
        }
    }else{
        results(Result.Failure("No Internet Connection", null))
    }*/
}

suspend fun <T> handlingResponse(response: suspend() -> Response<T>,
                                 results:(Result<T>) -> Unit) {
    try {
        val result = response()
        if (result.isSuccessful) {
            result.body()?.let { body ->
                results(Result.Success(body))
            }
        } else {
            Log.d("Result", result.toString())
            results(Result.Failure(result.toString(),result.code()))
        }
    } catch (throwable: Throwable) {
        results(Result.Failure(throwable.toString(),null))
    }
}