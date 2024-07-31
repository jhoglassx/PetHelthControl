package com.jhoglas.pethelthcontrol.ui.register.model

import com.jhoglas.domain.entity.GenderEntity
import com.jhoglas.domain.entity.RaceEntity

data class RegisterState (
    val isLoading : Boolean = false,
    val genders:List<GenderEntity> = emptyList(),
    val races:List<RaceEntity> = emptyList()
)