package com.jhoglas.data.repository

import com.jhoglas.data.remote.datasource.SexDataSource
import com.jhoglas.data.remote.entity.SexResponseRemoteEntity
import com.jhoglas.domain.entity.SexEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last


class SexRepositoryImpl(
    private val dataSource: SexDataSource
): com.jhoglas.domain.repository.SexRepository {

    override fun getSex(): Flow<SexEntity> = flow {
        val response: SexResponseRemoteEntity = dataSource.getSex().last()
        emit(response.toDomain())
    }
}