package com.jhoglas.pethelthcontrol.ui.register.model

import com.jhoglas.domain.entity.PetEntity

sealed class RegisterAction {
    data object LoadGenders : RegisterAction()
    data object LoadRaces : RegisterAction()
    data class SavePet(val pet: PetEntity?) : RegisterAction()
    data class EnableSaveButton(val pet: PetEntity?) : RegisterAction()
}