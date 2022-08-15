package com.mintic22g1.recetapp.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import com.mintic22g1.recetapp.data.datasource.MemoryDataSource
import com.mintic22g1.recetapp.data.db.dao.RecipeDao
import com.mintic22g1.recetapp.data.models.RecipeItemModel
import com.mintic22g1.recetapp.data.models.ServiceItemModel
import com.mintic22g1.recetapp.utils.RECIPE_COLLECTION
import kotlinx.coroutines.tasks.await

class HomeRepository(private val memoryDataSource: MemoryDataSource,
    private val recipeDao: RecipeDao,
    private val fireStoreService: FirebaseFirestore,
    private val storageService: FirebaseStorage) {

    suspend fun services(): List<ServiceItemModel> {
        return memoryDataSource.getServices()
    }

    suspend fun recipes(category: String?): List<RecipeItemModel> {
        val result: List<RecipeItemModel>
        if (category == null) {
            result = (fireStoreService.collection(RECIPE_COLLECTION).get().await()).toObjects<RecipeItemModel>()
        } else {
            result = (fireStoreService.collection(RECIPE_COLLECTION)
                .whereEqualTo("category", category).get().await())
                .toObjects()
        }

        val transfored = result.map {
            val ref = storageService.reference
            val imageRef = ref.child(it.image)
            it.image = (imageRef.downloadUrl.await()).toString()
            return@map it
        }

        return transfored
    }
}