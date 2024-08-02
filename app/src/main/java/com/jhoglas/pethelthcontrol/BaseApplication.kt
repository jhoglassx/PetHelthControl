package com.jhoglas.pethelthcontrol

import android.app.Application
import com.jhoglas.di.apiModule
import com.jhoglas.di.dataSourceModule
import com.jhoglas.di.headerModule
import com.jhoglas.di.repositoriesModule
import com.jhoglas.domain.di.domainModule
import com.jhoglas.infrastructure.di.infrastructureModule
import com.jhoglas.pethelthcontrol.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(modules)
        }
    }
}

val modules = listOf(
    domainModule,
    infrastructureModule,
    headerModule,
    apiModule,
    dataSourceModule,
    repositoriesModule,
    viewModelModule
)