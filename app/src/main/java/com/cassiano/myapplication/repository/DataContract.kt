package com.cassiano.myapplication.repository

import com.cassiano.myapplication.home.model.DataResult
import com.cassiano.myapplication.home.model.Recipe

interface DataContract {

    suspend fun getRecipes(): DataResult<List<Recipe>>

}