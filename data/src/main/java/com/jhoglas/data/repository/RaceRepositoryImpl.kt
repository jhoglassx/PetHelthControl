package com.jhoglas.data.repository

import com.jhoglas.data.remote.datasource.RaceDataSource
import com.jhoglas.domain.entity.RacesEntity
import com.jhoglas.domain.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class RaceRepositoryImpl(
    private val dataSource: RaceDataSource
): RaceRepository{

    override fun getRaces(): Flow<RacesEntity>  = dataSource
        .getRaces()
        .map {
            it.toDomain()
        }
}