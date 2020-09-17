package com.cassiano.myapplication.di

import com.cassiano.myapplication.home.view.viewmodel.MainViewModel
import com.cassiano.myapplication.home.view.viewmodel.RecipeDetailViewModel
import com.cassiano.myapplication.network.RetrofitClient
import com.cassiano.myapplication.repository.DataRepository
import com.cassiano.myapplication.utils.ResourceManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val serviceModule = module {
    single { RetrofitClient.getApiService() }
}

private val repositoryModule = module {
    single { DataRepository(service = get()) }
}

private val resourceManager = module {
    single { ResourceManager(context = get()) }
}

private val viewModelModule = module {
    viewModel { MainViewModel(repository = get()) }
    viewModel { RecipeDetailViewModel(resourceManager = get()) }
}

fun loadKoinModules() {
    loadKoinModules(listOf(serviceModule, repositoryModule, resourceManager, viewModelModule))
}
