package com.android.coding.challenge.di.module

import com.android.coding.challenge.module.repository.MoviesRepository
import com.android.coding.challenge.module.viewmodel.MoviesListViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {


    factory {
        (scope: CoroutineScope) -> MoviesRepository(scope = scope)
    }

    /**
     * Movies List ViewModel
     */
    viewModel {
        MoviesListViewModel()
    }

}
