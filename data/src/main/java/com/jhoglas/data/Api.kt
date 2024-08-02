package com.jhoglas.data

import com.jhoglas.data.remote.entity.GendersResponseRemoteEntity
import com.jhoglas.data.remote.entity.PetRequestRemoteEntity
import com.jhoglas.data.remote.entity.PetResponseRemoteEntity
import com.jhoglas.data.remote.entity.RacesResponseRemoteEntity
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun getGender(
        @Url endpoint: String
    ) : Response<GendersResponseRemoteEntity>

    @GET
    suspend fun getRaces(
        @Url endpoint: String
    ) : Response<RacesResponseRemoteEntity>

    @POST
    suspend fun createPet(
        @Url endpoint: String,
        @Part petRequestRemoteEntity: PetRequestRemoteEntity,
        @Part image: MultipartBody.Part? = null
    ) : Response<PetResponseRemoteEntity>
}