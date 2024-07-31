package com.jhoglas.data.remote.datasource

import com.jhoglas.data.remote.entity.RacesResponseRemoteEntity
import kotlinx.coroutines.flow.Flow


interface RaceDataSource {

    fun getRaces(): Flow<RacesResponseRemoteEntity>

}