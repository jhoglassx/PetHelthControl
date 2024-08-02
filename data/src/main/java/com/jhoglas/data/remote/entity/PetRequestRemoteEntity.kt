package com.jhoglas.data.remote.entity

import android.content.Context
import com.jhoglas.domain.entity.PetEntity
import com.jhoglas.infrastructure.toMultipartBodyPart
import okhttp3.MultipartBody

data class PetRequestRemoteEntity(
    var name: String? = null,
    var surname: String? = null,
    var dateBorn: String? = null,
    var weight: Double? = null,
    var gender: Int? = null,
    var race: Int? = null,
    val species: Int? = null,
    val hair: PetHairRequestRemoteEntity? = PetHairRequestRemoteEntity(),
    var image: MultipartBody.Part? = null
)
fun PetEntity.toRemote(
    context: Context
) = PetRequestRemoteEntity(
    name = this.name,
    surname = this.surname,
    dateBorn = this.dateBorn,
    weight = this.weight,
    gender = this.gender,
    race = this.race,
    species = this.species,
    hair = this.hair?.toRemote(),
    image = this.image?.toMultipartBodyPart(
        partName = this.name ?: "image",
        mediaType = "image/png",
        context = context
    )
)

