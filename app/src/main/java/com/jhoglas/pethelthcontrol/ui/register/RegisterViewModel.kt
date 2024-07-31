package com.jhoglas.pethelthcontrol.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhoglas.domain.repository.GenderRepository
import com.jhoglas.domain.repository.RaceRepository
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterAction
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class RegisterViewModel(
    private val genderRepository: GenderRepository,
    private val raceRepository: RaceRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.LoadGenders -> getGenders()
            is RegisterAction.LoadRaces -> getRaces()
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
}