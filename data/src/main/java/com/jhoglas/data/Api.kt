package com.jhoglas.data

import com.jhoglas.data.remote.entity.RaceResponseRemoteEntity
import com.jhoglas.data.remote.entity.SexResponseRemoteEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun getSex(
        @Url endpoint: String
    ) : Response<SexResponseRemoteEntity>

    @GET
    suspend fun getRace(
        @Url endpoint: String
    ) : Response<RaceResponseRemoteEntity>
}