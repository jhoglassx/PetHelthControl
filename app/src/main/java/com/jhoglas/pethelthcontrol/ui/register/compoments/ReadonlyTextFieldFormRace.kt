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
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterState


@Composable
fun ReadonlyTextFieldFormRace(
    uiState: RegisterState,
    testTag: String,
    race: (Int) -> Unit
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
                text = "Race",
            )
        },
        isLoading = uiState.isLoading,
        testTag = testTag
    )


    if(showBottomSheet && uiState.isLoading.not()){
        SelectionBottomSheetRace(
            title = "Select Race",
            list = uiState.races,
            valueSelected = {
                valueSelected = it
                race(it.id)
                showBottomSheet = false
            },
            testTag = testTag
        )
    }
}

@Preview
@Composable
fun ReadonlyTextFieldFormRacePreview() {
    ReadonlyTextFieldFormRace(
        uiState = RegisterState(
            isLoading = false,
            races = listOf(
                RaceEntity(id = 1, name = "Vira-lata", active = true),
                RaceEntity(id = 2, name = "Tomba-lixo", active = true),
            )
        ),
        race = {},
        testTag = "ReadonlyTextFieldFormRace"
    )
}
