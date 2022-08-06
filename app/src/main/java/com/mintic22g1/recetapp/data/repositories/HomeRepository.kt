package com.mintic22g1.recetapp.data.repositories

import com.mintic22g1.recetapp.data.datasource.MemoryDataSource
import com.mintic22g1.recetapp.data.models.RecipeItemModel
import com.mintic22g1.recetapp.data.models.ServiceItemModel

class HomeRepository(private val memoryDataSource: MemoryDataSource) {
    suspend fun services(): List<ServiceItemModel> {
        return memoryDataSource.getServices()
    }

    suspend fun recipes(category: String?): List<RecipeItemModel> {
        var recipes = memoryDataSource.getRecipes()
        if(category != null) {
            recipes = recipes.filter { it.category == category }
        }
        return recipes
    }
}