package com.mintic22g1.recetapp.di

import com.mintic22g1.recetapp.data.viewmodels.HomeViewModel
import com.mintic22g1.recetapp.data.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}
