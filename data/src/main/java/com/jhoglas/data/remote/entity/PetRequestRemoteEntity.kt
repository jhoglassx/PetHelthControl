package com.jhoglas.data.remote.entity

import android.net.Uri
import com.jhoglas.domain.entity.PetEntity

data class PetRequestRemoteEntity(
    var name: String? = null,
    var surname: String? = null,
    var dateBorn: String? = null,
    var weight: Double? = null,
    var gender: Int? = null,
    var race: Int? = null,
    val species: Int? = null,
    val hair: PetHairRequestRemoteEntity? = PetHairRequestRemoteEntity()
)
fun PetEntity.toRemote() = PetRequestRemoteEntity(
    name = this.name,
    surname = this.surname,
    dateBorn = this.dateBorn,
    weight = this.weight,
    gender = this.gender,
    race = this.race,
    species = this.species,
    hair = this.hair?.toRemote(),
)

