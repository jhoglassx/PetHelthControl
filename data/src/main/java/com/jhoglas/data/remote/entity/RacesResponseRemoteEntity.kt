package com.jhoglas.data.remote.entity

import android.os.Parcelable
import com.jhoglas.domain.entity.RacesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class RacesResponseRemoteEntity(
    val races: List<RaceResponseRemoteEntity>,
) : Parcelable{
    fun toDomain() = RacesEntity(
        races = races.map { it.toDomain() }
    )
}