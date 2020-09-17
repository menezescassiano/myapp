package com.cassiano.myapplication.repository

import com.cassiano.myapplication.home.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {

    @GET("recipes.json")
    suspend fun getRecipes(): Response<List<Recipe>>
}
