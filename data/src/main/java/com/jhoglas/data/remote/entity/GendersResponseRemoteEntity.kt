package com.jhoglas.data.remote.entity

import android.os.Parcelable
import com.jhoglas.domain.entity.GendersEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class GendersResponseRemoteEntity(
    val genders : List<GenderResponseRemoteEntity>
):Parcelable {
    fun toDomain() = GendersEntity(
        genders = genders.map { it.toDomain() }
    )
}
