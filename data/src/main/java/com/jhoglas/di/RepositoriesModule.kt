package com.jhoglas.di

import com.jhoglas.data.repository.GenderRepositoryImpl
import com.jhoglas.data.repository.PetRepositoryImpl
import com.jhoglas.data.repository.RaceRepositoryImpl
import com.jhoglas.domain.repository.GenderRepository
import com.jhoglas.domain.repository.PetRepository
import com.jhoglas.domain.repository.RaceRepository
import org.koin.dsl.module


val repositoriesModule = module {
    single<GenderRepository> {
        GenderRepositoryImpl(
            dataSource = get()
        )
    }

    single<RaceRepository> {
        RaceRepositoryImpl(
            dataSource = get()
        )
    }

    single<PetRepository> {
        PetRepositoryImpl(
            dataSource = get(),
            context = get()
        )
    }
}