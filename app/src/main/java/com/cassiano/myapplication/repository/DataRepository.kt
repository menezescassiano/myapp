package com.cassiano.myapplication.repository

import com.cassiano.myapplication.home.model.DataResult
import com.cassiano.myapplication.home.model.Recipe

class DataRepository(private val service: ServiceAPI) : RequestHelper(), DataContract {

    override suspend fun getRecipes(): DataResult<List<Recipe>> = apiRequest { service.getRecipes() }
}
