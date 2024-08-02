package com.jhoglas.data.remote.entity

import com.jhoglas.domain.entity.Hair

data class PetHairResponseRemoteEntity(
    var type: String? = null,
    var color: String? = null
)
fun PetHairResponseRemoteEntity.toDomain() = Hair(
    type = this.type,
    color = this.color
)
