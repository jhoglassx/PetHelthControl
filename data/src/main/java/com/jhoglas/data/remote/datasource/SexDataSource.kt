package com.jhoglas.data.remote.datasource

import com.jhoglas.data.Api
import com.jhoglas.data.remote.entity.SexResponseRemoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface SexDataSource {

    fun getSex(): Flow<SexResponseRemoteEntity>

}