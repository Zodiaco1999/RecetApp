package com.mintic22g1.recetapp.data.models

data class RecipeItemModel(
    val id: String,
    val name: String,
    val category: String,
    val duration: String,
    val image: Int,
    val star: Int
)