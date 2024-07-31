package com.jhoglas.di

import com.jhoglas.data.repository.GenderRepositoryImpl
import com.jhoglas.data.repository.RaceRepositoryImpl
import com.jhoglas.domain.repository.GenderRepository
import com.jhoglas.domain.repository.RaceRepository
import org.koin.dsl.module


val repositoriesModule = module {
    factory<GenderRepository> {
        GenderRepositoryImpl(
            dataSource = get()
        )
    }

    factory<RaceRepository> {
        RaceRepositoryImpl(
            dataSource = get()
        )
    }
}