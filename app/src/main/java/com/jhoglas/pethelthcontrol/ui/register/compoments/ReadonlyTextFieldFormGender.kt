package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.jhoglas.domain.entity.GenderEntity
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterState


@Composable
fun ReadonlyTextFieldFormGender(
    uiState: RegisterState,
    gender: (Int) -> Unit
){
    var showBottomSheet by remember { mutableStateOf(false) }
    var valueSelected by remember { mutableStateOf<GenderEntity?>(null) }

    ReadonlyTextField(
        value = TextFieldValue(
            text = valueSelected?.name ?: "Selecione um Gênero"
        ) ,
        onValueChange = {},
        onClick = {
            showBottomSheet = true
        },
        label = {
            Text(
                text = "Gender",
            )
        },
        isLoading = uiState.isLoading
    )

    if(showBottomSheet && uiState.isLoading.not()){
        SelectionBottomSheetGender(
            title = "Select Gender",
            list = uiState.genders,
            valueSelected = {
                valueSelected = it
                gender(it.id)
                showBottomSheet = false
            }
        )
    }
}

@Preview
@Composable
fun ReadonlyTextFieldFormGenderPreview() {
    ReadonlyTextFieldFormGender(
        uiState = RegisterState(
            isLoading = false,
            genders = listOf(
                GenderEntity(id = 1, name = "Macho", active = true),
                GenderEntity(id = 2, name = "Fêmea", active = true),
            )
        ),
        gender = {}
    )
}