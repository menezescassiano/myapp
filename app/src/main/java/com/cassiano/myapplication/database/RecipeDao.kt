package com.cassiano.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<RecipeObject>

    @Query("SELECT * FROM recipe WHERE id IN (:id) LIMIT 1")
    suspend fun getById(id: String): RecipeObject

    @Query("SELECT * FROM recipe WHERE title LIKE :title LIMIT 1")
    suspend fun findByUrl(title: String): RecipeObject

    @Insert
    suspend fun insertAll(vararg recipes: RecipeObject)

    @Delete
    suspend fun delete(recipe: RecipeObject)

}