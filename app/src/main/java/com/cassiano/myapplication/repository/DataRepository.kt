package com.cassiano.myapplication.repository

import com.cassiano.myapplication.home.model.Recipe
import retrofit2.Response

class DataRepository(private val service: ServiceAPI) : ServiceAPI {

    override suspend fun getRecipes(): Response<List<Recipe>> = service.getRecipes()
}
