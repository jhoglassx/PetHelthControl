package com.jhoglas.infrastructure

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import java.util.concurrent.TimeUnit

const val REQUEST_TRACE_ID = "requestTraceId"

object ServiceFactory {
    inline fun <reified T> createService(
        parameters: ServiceFactoryParameters
    ): T = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(parameters.baseUrl)
        .client(buildOkHttpClient(parameters))
        .build()
        .create(T::class.java)

    fun buildOkHttpClient(
        parameters: ServiceFactoryParameters
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(parameters.connectTimeout, TimeUnit.SECONDS)
            .readTimeout(parameters.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(parameters.writeTimeout, TimeUnit.SECONDS)
            .addInterceptor(GzipInterceptor())

        parameters.authenticator?.let {
            builder.authenticator(it)
        }

        if (parameters.headers.isNotEmpty()) {
            builder.addInterceptor(providesHeaderInterceptor(parameters.headers))
        }

        if (parameters.interceptors.isNotEmpty()) {
            parameters.interceptors.iterator().forEach {
                builder.addInterceptor(it)
            }
        }
        return builder.build()
    }

    private fun providesHeaderInterceptor(headers: HashMap<String, String>) =
        Interceptor { interceptor ->
            interceptor.proceed(
                interceptor.request().newBuilder()
                    .apply {
                        for (i in headers.keys) {
                            addHeader(i, headers[i].orEmpty())
                        }
                        addHeader(REQUEST_TRACE_ID, UUID.randomUUID().toString())
                    }
                    .build()
            )
        }
}