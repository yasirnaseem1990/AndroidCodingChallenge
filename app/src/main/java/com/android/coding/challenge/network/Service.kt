package com.android.coding.challenge.network


import com.android.coding.challenge.module.model.moviesListResponseModel.MoviesListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("movies")
    suspend fun getMoviesList(): Response<MoviesListResponseModel>
}