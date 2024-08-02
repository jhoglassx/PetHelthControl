package com.jhoglas.data.remote.entity

import com.jhoglas.domain.entity.Hair

data class PetHairRequestRemoteEntity(
    var type: String? = null,
    var color: String? = null
)
fun Hair.toRemote() = PetHairRequestRemoteEntity(
    type = this.type,
    color = this.color
)
