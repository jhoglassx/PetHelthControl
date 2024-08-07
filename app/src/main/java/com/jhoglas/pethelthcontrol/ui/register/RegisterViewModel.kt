package com.jhoglas.pethelthcontrol.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhoglas.domain.entity.PetEntity
import com.jhoglas.domain.repository.GenderRepository
import com.jhoglas.domain.repository.PetRepository
import com.jhoglas.domain.repository.RaceRepository
import com.jhoglas.infrastructure.isNullOrZero
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterAction
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val genderRepository: GenderRepository,
    private val raceRepository: RaceRepository,
    private val petRepository: PetRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.LoadGenders -> getGenders()
            is RegisterAction.LoadRaces -> getRaces()
            is RegisterAction.SavePet -> savePet(action.pet)
            is RegisterAction.EnableSaveButton -> isEnableSaveButton(action.pet)
        }
    }

    private fun getGenders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            genderRepository.getGenders().collect { genders ->
                _uiState.update { state ->
                    state.copy(
                        genders = genders.genders
                    )
                }
                Log.i("RegisterViewModel -> getGenders()", genders.toString())
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun getRaces() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            raceRepository.getRaces().collect { races ->
                _uiState.update { state ->
                    state.copy(
                        races = races.races
                    )
                }
                Log.i("RegisterViewModel -> getRaces()", races.toString())
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun savePet(
        pet: PetEntity?
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            pet?.let {
                petRepository.createPet(it).collect { petEntity ->
                    petEntity?.let {
                        _uiState.update { state ->
                            state.copy(
                                pet = petEntity
                            )
                        }
                    }
                    Log.i("RegisterViewModel -> savePet()", petEntity.toString())
                }
            }

            Log.i("RegisterViewModel -> savePet()", pet.toString())
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun isEnableSaveButton(pet: PetEntity?) {
        pet?.let { pet ->
            val saveButtonIsEnabled =
                pet.name.isNullOrEmpty().not() &&
                pet.surname.isNullOrEmpty().not() &&
                pet.race.isNullOrZero().not() &&
                pet.gender.isNullOrZero().not() &&
                pet.dateBorn.isNullOrEmpty().not() &&
                pet.hair?.color.isNullOrEmpty().not() &&
                pet.hair?.type.isNullOrEmpty().not() &&
                pet.weight.isNullOrZero().not()

            _uiState.update {
                it.copy(saveButtonIsEnabled = saveButtonIsEnabled)
            }
        }
    }
}