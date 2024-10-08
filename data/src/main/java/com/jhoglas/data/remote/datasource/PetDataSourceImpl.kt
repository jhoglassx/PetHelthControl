package com.jhoglas.data.remote.datasource

import android.util.Log
import com.jhoglas.data.Api
import com.jhoglas.data.remote.entity.PetRequestRemoteEntity
import com.jhoglas.data.remote.entity.PetResponseRemoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class PetDataSourceImpl(
    private val api: Api
): PetDataSource {
    override suspend fun getPet(): Flow<PetResponseRemoteEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getPetById(id: Int): Flow<PetResponseRemoteEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun createPet(
        petRequestRemoteEntity: PetRequestRemoteEntity,
        image: MultipartBody.Part?
    ): Flow<PetResponseRemoteEntity> = flow {
        try {
            val response = api.createPet(
                endpoint = "/pet",
                petRequestRemoteEntity = petRequestRemoteEntity,
                image = image
            )
            Log.i("PetDataSource -> createPet()", response.toString())
            response.body()?.let { emit(it) }
        } catch (e: Exception) {
            Log.e("PetDataSource -> createPet()", e.toString())
        }
    }

    override suspend fun updatePet(): Flow<PetResponseRemoteEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePet(): Flow<PetResponseRemoteEntity> {
        TODO("Not yet implemented")
    }
}
