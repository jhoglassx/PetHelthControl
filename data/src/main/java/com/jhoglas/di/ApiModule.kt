package com.jhoglas.di

import com.jhoglas.data.Api
import com.jhoglas.infrastructure.ServiceFactory
import com.jhoglas.infrastructure.ServiceFactoryParameters
import com.jhoglas.infrastructure.di.HTTP_HEADERS
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiModule = module {
    single<Api> {
        ServiceFactory.createService(
            parameters = ServiceFactoryParameters(
                baseUrl = "https://api.github.com",
                headers = get(named(HTTP_HEADERS))
            )
        )
    }
}
