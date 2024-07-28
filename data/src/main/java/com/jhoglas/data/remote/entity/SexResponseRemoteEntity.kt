package com.jhoglas.data.remote.entity

import android.os.Parcelable
import com.jhoglas.domain.entity.SexEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class SexResponseRemoteEntity(
    val id: Int,
    val name: String,
    val active: Boolean = true,
) : Parcelable {

    fun toDomain() = SexEntity(
        id = id,
        name = name,
        active = active
    )

}

