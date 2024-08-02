package com.jhoglas.pethelthcontrol.di

import com.jhoglas.pethelthcontrol.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        RegisterViewModel(
            genderRepository = get(),
            raceRepository = get(),
            petRepository = get()
        )
    }
}