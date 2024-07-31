package com.jhoglas.data.remote.datasource

import android.util.Log
import com.jhoglas.data.Api
import com.jhoglas.data.remote.entity.RacesResponseRemoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RaceDataSourceImpl(
    private val api: Api
): RaceDataSource {

    override fun getRaces(): Flow<RacesResponseRemoteEntity> {
        return flow {
            val response = api.getRaces(
                endpoint = "/races"
            )
            Log.i("RaceDataSource", response.toString())
            response.body()?.let { emit(it) }
        }
    }
}