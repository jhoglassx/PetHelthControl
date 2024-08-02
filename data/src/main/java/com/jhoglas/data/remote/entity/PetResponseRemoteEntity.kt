package com.jhoglas.data.remote.entity

import androidx.core.net.toUri
import com.jhoglas.domain.entity.PetEntity

data class PetResponseRemoteEntity(
    var name: String? = null,
    var surname: String? = null,
    var dateBorn: String? = null,
    var weight: Double? = null,
    var gender: Int? = null,
    var race: Int? = null,
    val species: Int? = null,
    val hair: PetHairResponseRemoteEntity? = PetHairResponseRemoteEntity(),
    var image: String
)
fun PetResponseRemoteEntity.toDomain() = PetEntity(
    name = this.name,
    surname = this.surname,
    dateBorn = this.dateBorn,
    weight = this.weight,
    gender = this.gender,
    race = this.race,
    species = this.species,
    hair = this.hair?.toDomain(),
    image = this.image.toUri()
)

