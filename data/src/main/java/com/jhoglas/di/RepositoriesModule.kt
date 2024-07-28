package com.jhoglas.di

import com.jhoglas.data.repository.SexRepositoryImpl
import com.jhoglas.domain.repository.SexRepository
import org.koin.dsl.module


val repositoriesModule = module {
    factory<SexRepository> {
        SexRepositoryImpl(
            dataSource = get()
        )
    }
}