package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.jhoglas.domain.entity.RaceEntity
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterAction
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterState


@Composable
fun ReadonlyTextFieldFormRace(
    label: String,
    bottomSheetTitle: String,
    uiState: RegisterState,
){
    var showBottomSheet by remember { mutableStateOf(false) }
    var valueSelected by remember { mutableStateOf<RaceEntity?>(null) }

    ReadonlyTextField(
        value = TextFieldValue(
            text = valueSelected?.name ?: "Selecione uma Raça"
        ) ,
        onValueChange = {},
        onClick = {
            showBottomSheet = true
        },
        label = {
            Text(
                text = label,
            )
        },
        isLoading = uiState.isLoading
    )


    if(showBottomSheet && uiState.isLoading.not()){
        SelectionBottomSheetRace(
            title = bottomSheetTitle,
            list = uiState.races,
            valueSelected = {
                valueSelected = it
                showBottomSheet = false
            }
        )
    }
}

@Preview
@Composable
fun ReadonlyTextFieldFormRacePreview() {
    ReadonlyTextFieldFormRace(
        label = "Raça",
        bottomSheetTitle = "Selecione a raça do seu pet",
        uiState = RegisterState(
            isLoading = false,
            races = listOf(
                RaceEntity(id = 1, name = "Vira-lata", active = true),
                RaceEntity(id = 2, name = "Tomba-lixo", active = true),
            )
        )
    )
}
