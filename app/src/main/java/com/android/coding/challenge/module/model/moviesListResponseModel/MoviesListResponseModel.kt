package com.android.coding.challenge.module.model.moviesListResponseModel


import com.google.gson.annotations.SerializedName

/**
 * Created on 9/28/2019
 * Email ynaseem@an10.io
 * Organization AN10
 */

data class MoviesListResponseModel(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)