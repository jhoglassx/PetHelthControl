package com.jhoglas.di


import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.annotation.processing.Generated

@Generated
val headerModule: Module = module {

    single(named(HTTP_HEADER)) {
        val headersMap = HashMap<String, String>()
        headersMap["Content-Type"] = "application/json"
        headersMap["cache-control"] = "no-cache"
        headersMap["Accept"] = "application/json"

        headersMap
    }
}