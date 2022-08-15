package com.mintic22g1.recetapp.di

import androidx.room.Room
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.mintic22g1.recetapp.data.datasource.MemoryDataSource
import com.mintic22g1.recetapp.data.db.AppDatabase
import com.mintic22g1.recetapp.data.db.dao.RecipeDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {
    single { MemoryDataSource() }
    single<AppDatabase> {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "dbrecetapp").build()
    }
    single<RecipeDao> {
        get<AppDatabase>().recipeDao()
    }
    single {
        Firebase.auth
    }
    single {
        Firebase.firestore
    }
    single {
        Firebase.storage("gs://recetapp-30603.appspot.com")
    }
}