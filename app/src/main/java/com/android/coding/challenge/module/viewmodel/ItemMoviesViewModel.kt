package com.android.coding.challenge.module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.coding.challenge.module.model.moviesListResponseModel.DataItem

class ItemMoviesViewModel (val movieItem: DataItem) {

    val data = MutableLiveData<DataItem>()

    init {
        data.value = movieItem
    }

}