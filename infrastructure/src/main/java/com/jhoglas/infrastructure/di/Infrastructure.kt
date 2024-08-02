package com.jhoglas.infrastructure.di

import com.jhoglas.infrastructure.ImageService
import org.koin.dsl.module


val infrastructureModule = module {
    single {
        ImageService(
            context = get()
        )
    }
}