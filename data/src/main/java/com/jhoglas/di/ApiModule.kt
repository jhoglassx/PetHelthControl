package com.jhoglas.di

import com.jhoglas.data.Api
import com.jhoglas.infrastructure.ServiceFactory
import com.jhoglas.infrastructure.ServiceFactoryParameters
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val HTTP_HEADER= "http_header"

val apiModule = module {
    single<Api> {
        ServiceFactory.createService(
            parameters = ServiceFactoryParameters(
                baseUrl = "",
                headers = get(named(HTTP_HEADER))
            )
        )
    }
}
