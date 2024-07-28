package com.jhoglas.domain.repository

import com.jhoglas.domain.entity.SexEntity
import kotlinx.coroutines.flow.Flow


interface SexRepository {

    fun getSex(): Flow<SexEntity>

}