package com.android.coding.challenge.module.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.coding.challenge.helper.SingleLiveEvent
import com.android.coding.challenge.module.model.moviesListResponseModel.DataItem
import com.android.coding.challenge.module.repository.MoviesRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MoviesListViewModel : ViewModel(), KoinComponent {

    private val newsRepository by inject<MoviesRepository> { parametersOf(viewModelScope) }

    val showLoading = MutableLiveData<Boolean>()
    val moviesList = MutableLiveData<List<DataItem>>()
    val showError = SingleLiveEvent<String>()

    init {
        getMoviesData()
    }

    private fun getMoviesData() {
        showLoading.value = true
        newsRepository.getMoviesList() { list, error ->
            showLoading.value = false
            list?.let {
                moviesList.value = it
            }
            error?.let {
                showError.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}