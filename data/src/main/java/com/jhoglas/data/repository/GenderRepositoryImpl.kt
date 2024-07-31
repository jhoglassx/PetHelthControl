package com.jhoglas.data.repository

import com.jhoglas.data.remote.datasource.GenderDataSource
import com.jhoglas.domain.entity.GendersEntity
import com.jhoglas.domain.repository.GenderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GenderRepositoryImpl(
    private val dataSource: GenderDataSource
): GenderRepository {

    override fun getGenders(): Flow<GendersEntity> = dataSource
        .getGenders()
        .map {
            it.toDomain()
        }
}