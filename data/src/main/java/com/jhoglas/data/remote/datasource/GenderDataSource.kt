package com.jhoglas.data.remote.datasource

import com.jhoglas.data.remote.entity.GendersResponseRemoteEntity
import kotlinx.coroutines.flow.Flow


interface GenderDataSource {

    fun getGenders(): Flow<GendersResponseRemoteEntity>

}