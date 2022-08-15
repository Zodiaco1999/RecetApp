package com.mintic22g1.recetapp.data.datasource

import com.mintic22g1.recetapp.R
import com.mintic22g1.recetapp.data.models.RecipeItemModel
import com.mintic22g1.recetapp.data.models.ServiceItemModel

class MemoryDataSource {
    fun getRecipes(): List<RecipeItemModel> {
        return listOf(
            RecipeItemModel("1", "Muffins de mora", "Frutas", "60", R.drawable.re_fruits_1.toString(), 5),
            RecipeItemModel("2", "Cupcake de banano con arequipe", "Frutas", "85", R.drawable.re_fruits_2.toString(), 4),
            RecipeItemModel("3", "Bowl de avena", "Frutas", "16", R.drawable.re_fruits_3.toString(), 5),
            RecipeItemModel("4", "Picada típica", "Carnes", "24", R.drawable.re_meat_1.toString(), 5),
            RecipeItemModel("5", "Guiso de cola", "Carnes", "65", R.drawable.re_meat_2.toString(), 3),
            RecipeItemModel("6", "Carne con yuca y zanahoria", "Carnes", "60", R.drawable.re_meat_3.toString(), 5),
            RecipeItemModel("7", "Arroz paisa con salsa", "Cerdo", "45", R.drawable.re_pork_1.toString(), 4),
            RecipeItemModel("8", "Mondongo", "Cerdo", "122", R.drawable.re_pork_2.toString(), 4),
            RecipeItemModel("9", "Costillas BBQ de cerdo ahumadas", "Cerdo", "40", R.drawable.re_pork_3.toString(), 3),
            RecipeItemModel("10", "Omelette de claras con vegetales", "Vegetales", "16", R.drawable.re_vegetables_1.toString(),5),
            RecipeItemModel("11", "Ensalada de tomate, pepino, pimenton y pan frito", "Vegetales",
                "12", R.drawable.re_vegetables_2.toString(), 3),
            RecipeItemModel("12", "Ensalada de manzana y rabanos con salsa cremosa", "Vegetales",
                "15", R.drawable.re_vegetables_3.toString(), 5)
        )
    }

    fun getServices(): List<ServiceItemModel>{
        return listOf(
            ServiceItemModel("6",  R.drawable.vegetales,  "Vegetales gourmet"),
            ServiceItemModel("3",  R.drawable.hamburguesa,  "Mega hamburguesa"),
            ServiceItemModel("1",  R.drawable.pizza,  "Pizza especial"),
            ServiceItemModel("2",  R.drawable.brochetas,  "Brochetas ahumadas"),
            ServiceItemModel("4",  R.drawable.sushi,  "Rollo primevera"),
            ServiceItemModel("5",  R.drawable.tacos,  "Tacos de carne")
        )
    }
}