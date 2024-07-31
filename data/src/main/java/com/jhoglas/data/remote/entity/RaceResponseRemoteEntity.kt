package com.jhoglas.data.remote.entity

import android.os.Parcelable
import com.jhoglas.domain.entity.RaceEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class RaceResponseRemoteEntity(
    val id: Int,
    val name: String,
    val active: Boolean = true,
) : Parcelable {
    fun toDomain() = RaceEntity(
        id = id,
        name = name,
        active = active
    )
}