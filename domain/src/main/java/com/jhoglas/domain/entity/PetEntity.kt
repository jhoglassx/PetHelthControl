package com.jhoglas.domain.entity

import android.net.Uri

data class PetEntity(
    var name: String? = null,
    var surname: String? = null,
    var dateBorn: String? = null,
    var weight: Double? = null,
    var gender: Int? = null,
    var race: Int? = null,
    val species: Int? = null,
    val hair: Hair? = Hair(),
    var image: Uri? = null
)
