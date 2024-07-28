package com.jhoglas.data.remote.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RaceResponseRemoteEntity(
    val id: Int,
    val name: String,
    val active: Boolean = true,
) : Parcelable