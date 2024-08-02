package com.jhoglas.domain.repository

import com.jhoglas.domain.entity.PetEntity
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    suspend fun createPet(petEntity: PetEntity): Flow<PetEntity?>
}