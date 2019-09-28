package com.android.coding.challenge.module.repository


import com.android.coding.challenge.module.model.moviesListResponseModel.DataItem
import com.android.coding.challenge.network.Result
import com.android.coding.challenge.network.Service
import com.android.coding.challenge.network.request
import kotlinx.coroutines.CoroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject


class MoviesRepository(private val scope: CoroutineScope) : KoinComponent {

    private val service by inject<Service>()

    /**
     * get Movies List
     */
    fun getMoviesList(response: (List<DataItem>?, String?) -> Unit) {
        request(scope, {service.getMoviesList()}, { res ->
            when(res) {
                is Result.Success -> response(res.data.data as List<DataItem>?, null)
                is Result.Failure -> response(null, res.error)
            }
        })
    }


}