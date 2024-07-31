package com.jhoglas.domain.entity

data class RaceEntity(
    val id: Int,
    val name: String,
    val active: Boolean = true,
)
