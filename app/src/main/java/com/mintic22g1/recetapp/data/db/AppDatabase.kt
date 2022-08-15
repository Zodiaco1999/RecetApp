package com.mintic22g1.recetapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mintic22g1.recetapp.data.db.dao.RecipeDao
import com.mintic22g1.recetapp.data.models.RecipeItemModel

@Database(entities = [RecipeItemModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}