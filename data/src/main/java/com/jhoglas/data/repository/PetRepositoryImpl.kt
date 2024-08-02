package com.jhoglas.data.repository

import android.content.Context
import android.util.Log
import com.jhoglas.data.remote.datasource.PetDataSource
import com.jhoglas.data.remote.entity.toDomain
import com.jhoglas.data.remote.entity.toRemote
import com.jhoglas.domain.entity.PetEntity
import com.jhoglas.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PetRepositoryImpl(
    private val dataSource: PetDataSource,
    private val context: Context
): PetRepository {

    override suspend fun createPet(
        petEntity: PetEntity
    ): Flow<PetEntity?> = flow {
        val petToRemote = petEntity.toRemote(context)

        dataSource.createPet(petToRemote)
            .catch { e ->
                Log.e("PetRepository -> savePetData()", "Error: ${e.message}", e)
                emit(null)
            }
            .collect { responseRemote ->
                val domainEntity = responseRemote.toDomain()
                Log.e("PetRepository -> savePetData()", domainEntity.toString())
                emit(domainEntity)
            }
    }
}