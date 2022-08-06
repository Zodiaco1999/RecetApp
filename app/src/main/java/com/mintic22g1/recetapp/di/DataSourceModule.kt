package com.mintic22g1.recetapp.di

import com.mintic22g1.recetapp.data.datasource.MemoryDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { MemoryDataSource() }
}