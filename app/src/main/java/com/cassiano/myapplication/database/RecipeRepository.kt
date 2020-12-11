package com.cassiano.myapplication.database

import com.cassiano.myapplication.home.model.DataResult
import com.cassiano.myapplication.home.model.Recipe

interface RecipeRepository {

    suspend fun insertAll(recipeObject: RecipeObject)

    suspend fun getAll(): List<RecipeObject>

    suspend fun delete(gif: RecipeObject)

    suspend fun findByUrl(id: String): RecipeObject

    suspend fun getById(id: String): RecipeObject

    suspend fun getMyData(recipeObjects: ArrayList<Recipe>): DataResult<ArrayList<Recipe>>

    suspend fun recipeCrud(recipeObject: RecipeObject)

}