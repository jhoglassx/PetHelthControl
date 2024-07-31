package com.jhoglas.domain.repository

import com.jhoglas.domain.entity.GendersEntity
import kotlinx.coroutines.flow.Flow


interface GenderRepository {

    fun getGenders(): Flow<GendersEntity>

}