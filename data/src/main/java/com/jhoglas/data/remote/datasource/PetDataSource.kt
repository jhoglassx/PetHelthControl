package com.jhoglas.data.remote.datasource

import com.jhoglas.data.remote.entity.PetRequestRemoteEntity
import com.jhoglas.data.remote.entity.PetResponseRemoteEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface PetDataSource {
    suspend fun getPet(): Flow<PetResponseRemoteEntity>
    suspend fun getPetById(id: Int): Flow<PetResponseRemoteEntity>
    suspend fun createPet(
        petRequestRemoteEntity: PetRequestRemoteEntity,
        image: MultipartBody.Part? = null
    ): Flow<PetResponseRemoteEntity>
    suspend fun updatePet(): Flow<PetResponseRemoteEntity>
    suspend fun deletePet(): Flow<PetResponseRemoteEntity>
}