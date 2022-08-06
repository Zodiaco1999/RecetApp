package com.mintic22g1.recetapp.interfaces

import com.mintic22g1.recetapp.data.models.RecipeItemModel

interface OnRecipeClickListener {
    fun onClick(item: RecipeItemModel)
}