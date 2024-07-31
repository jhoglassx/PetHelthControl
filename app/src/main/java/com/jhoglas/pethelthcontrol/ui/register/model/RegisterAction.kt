package com.jhoglas.pethelthcontrol.ui.register.model

sealed class RegisterAction {
    data object LoadGenders : RegisterAction()
    data object LoadRaces : RegisterAction()
}