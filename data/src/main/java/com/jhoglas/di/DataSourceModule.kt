package com.jhoglas.di

import com.jhoglas.data.remote.datasource.GenderDataSource
import com.jhoglas.data.remote.datasource.GenderDataSourceImpl
import com.jhoglas.data.remote.datasource.RaceDataSource
import com.jhoglas.data.remote.datasource.RaceDataSourceImpl
import org.koin.dsl.module


val dataSourceModule = module {
    factory <GenderDataSource> {
        GenderDataSourceImpl(
            api = get()
        )
    }
    factory <RaceDataSource> {
        RaceDataSourceImpl(
            api = get()
        )
    }
}