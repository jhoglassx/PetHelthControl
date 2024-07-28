package com.jhoglas.di

import com.jhoglas.data.remote.datasource.SexDataSource
import com.jhoglas.data.remote.datasource.SexDataSourceImpl
import org.koin.dsl.module


val dataSourceModule = module {
    factory <SexDataSource> {
        SexDataSourceImpl(
            api = get()
        )
    }
//    factory <SexDataSource> {
//        SexDataSourceImpl( api = get() )
//    }
}