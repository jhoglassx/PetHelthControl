package com.jhoglas.domain.repository

import com.jhoglas.domain.entity.GendersEntity
import com.jhoglas.domain.entity.RacesEntity
import kotlinx.coroutines.flow.Flow


interface RaceRepository {

    fun getRaces(): Flow<RacesEntity>

}