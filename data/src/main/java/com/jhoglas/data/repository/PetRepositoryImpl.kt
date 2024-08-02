package com.jhoglas.data.repository

import android.util.Log
import com.jhoglas.data.remote.datasource.PetDataSource
import com.jhoglas.data.remote.entity.toDomain
import com.jhoglas.data.remote.entity.toRemote
import com.jhoglas.domain.entity.PetEntity
import com.jhoglas.domain.repository.PetRepository
import com.jhoglas.infrastructure.ImageService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class PetRepositoryImpl(
    private val dataSource: PetDataSource,
    private val imageService: ImageService
): PetRepository {

    override suspend fun createPet(
        petEntity: PetEntity
    ): Flow<PetEntity?> = flow {
        val petToRemote = petEntity.toRemote()

        var imageUri: MultipartBody.Part? = null

        petEntity.image?.let {
            imageUri = imageService.toMultipartBodyPart(
                uri = it,
                partName = petEntity.name ?: "pet"
            )
        }

        dataSource.createPet(
            image = imageUri,
            petRequestRemoteEntity = petToRemote
        )
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