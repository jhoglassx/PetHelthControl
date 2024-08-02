package com.jhoglas.data.remote.datasource

import com.jhoglas.data.remote.entity.PetRequestRemoteEntity
import com.jhoglas.data.remote.entity.PetResponseRemoteEntity
import kotlinx.coroutines.flow.Flow

interface PetDataSource {
    suspend fun getPet(): Flow<PetResponseRemoteEntity>
    suspend fun getPetById(id: Int): Flow<PetResponseRemoteEntity>
    suspend fun createPet(
        petRequestRemoteEntity: PetRequestRemoteEntity
    ): Flow<PetResponseRemoteEntity>
    suspend fun updatePet(): Flow<PetResponseRemoteEntity>
    suspend fun deletePet(): Flow<PetResponseRemoteEntity>
}