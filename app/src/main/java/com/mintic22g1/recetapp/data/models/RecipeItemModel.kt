package com.mintic22g1.recetapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeItemModel(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val category: String = "",
    val duration: String = "",
    var image: String = "",
    val star: Int = 0,
    var ingredients: String = "",
)