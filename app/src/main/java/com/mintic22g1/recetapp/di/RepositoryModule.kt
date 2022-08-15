package com.mintic22g1.recetapp.di

import com.mintic22g1.recetapp.data.repositories.HomeRepository
import com.mintic22g1.recetapp.data.repositories.LoginRepository
import org.koin.dsl.module

val repoModule = module {
    single { LoginRepository(get(), get(), get()) }
    single { HomeRepository(get(), get(), get(), get()) }
}