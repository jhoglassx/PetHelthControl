package com.jhoglas.di

import com.jhoglas.data.remote.datasource.GenderDataSource
import com.jhoglas.data.remote.datasource.GenderDataSourceImpl
import com.jhoglas.data.remote.datasource.PetDataSource
import com.jhoglas.data.remote.datasource.PetDataSourceImpl
import com.jhoglas.data.remote.datasource.RaceDataSource
import com.jhoglas.data.remote.datasource.RaceDataSourceImpl
import org.koin.dsl.module


val dataSourceModule = module {
    single <GenderDataSource> {
        GenderDataSourceImpl(api = get())
    }
    single <RaceDataSource> {
        RaceDataSourceImpl(api = get())
    }
    single <PetDataSource> {
        PetDataSourceImpl(api = get())
    }
}