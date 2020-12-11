package com.cassiano.myapplication.database

import com.cassiano.myapplication.home.model.DataResult
import com.cassiano.myapplication.home.model.Recipe

class RecipeRepositoryImpl(val recipeDao: RecipeDao): RecipeRepository {

    override suspend fun insertAll(recipeObject: RecipeObject) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<RecipeObject> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(gif: RecipeObject) {
        TODO("Not yet implemented")
    }

    override suspend fun findByUrl(id: String): RecipeObject {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): RecipeObject {
        TODO("Not yet implemented")
    }

    override suspend fun getMyData(recipeObjects: ArrayList<Recipe>): DataResult<ArrayList<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun recipeCrud(recipeObject: RecipeObject) {
        TODO("Not yet implemented")
    }

}