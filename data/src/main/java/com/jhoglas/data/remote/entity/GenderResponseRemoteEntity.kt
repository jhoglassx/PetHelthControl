package com.jhoglas.data.remote.entity

import android.os.Parcelable
import com.jhoglas.domain.entity.GenderEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenderResponseRemoteEntity(
    val id: Int,
    val name: String,
    val active: Boolean = true,
) : Parcelable {

    fun toDomain() = GenderEntity(
        id = id,
        name = name,
        active = active
    )
}

