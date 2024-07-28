package com.jhoglas.data.remote.datasource

import com.jhoglas.data.Api
import com.jhoglas.data.remote.entity.SexResponseRemoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SexDataSourceImpl(
    private val api: Api
): SexDataSource {

    override fun getSex(): Flow<SexResponseRemoteEntity> {
        return flow {
            val response = api.getSex(
                endpoint = "/sex"
            )
            response.body()?.let { emit(it) }
        }
    }
}