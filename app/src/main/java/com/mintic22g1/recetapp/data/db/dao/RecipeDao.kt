package com.mintic22g1.recetapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mintic22g1.recetapp.data.models.RecipeItemModel

@Dao
interface RecipeDao {

    @Query("SElECT * FROM recipes")
    suspend fun getAll(): List<RecipeItemModel>

    @Query("SElECT * FROM recipes WHERE category = :category")
    suspend fun getAllByCategory(category: String): List<RecipeItemModel>

    @Query("SELECT COUNT(*) FROM recipes")
    suspend fun getCount(): Int

    @Insert
    suspend fun insertAll(service: List<RecipeItemModel>)
}