package com.jhoglas.data.remote.datasource

import android.util.Log
import com.jhoglas.data.Api
import com.jhoglas.data.remote.entity.GendersResponseRemoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GenderDataSourceImpl(
    private val api: Api
): GenderDataSource {

    override fun getGenders(): Flow<GendersResponseRemoteEntity> {
        return flow {
            val response = api.getGender(
                endpoint = "/genders"
            )
            Log.i("GenderDataSource", response.toString())
            response.body()?.let { emit(it) }
        }
    }
}