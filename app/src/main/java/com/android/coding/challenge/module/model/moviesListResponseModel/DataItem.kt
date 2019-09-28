package com.android.coding.challenge.module.model.moviesListResponseModel

import com.google.gson.annotations.SerializedName

/**
 * Created on 9/28/2019
 * Email ynaseem@an10.io
 * Organization AN10
 */

data class DataItem(

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("genre")
	val genre: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster")
	val poster: String? = null
)