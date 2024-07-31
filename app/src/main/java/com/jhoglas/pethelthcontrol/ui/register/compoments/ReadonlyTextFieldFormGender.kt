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
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterAction
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterState


@Composable
fun ReadonlyTextFieldFormGender(
    label: String,
    bottomSheetTitle: String,
    uiState: RegisterState,
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
                text = label
            )
        },
        isLoading = uiState.isLoading
    )

    if(showBottomSheet && uiState.isLoading.not()){
        SelectionBottomSheetGender(
            title = bottomSheetTitle,
            list = uiState.genders,
            valueSelected = {
                valueSelected = it
                showBottomSheet = false
            }
        )
    }
}

@Preview
@Composable
fun ReadonlyTextFieldFormGenderPreview() {
    ReadonlyTextFieldFormGender(
        label = "Gênero",
        bottomSheetTitle = "Selecione o gênero do seu pet",
        uiState = RegisterState(
            isLoading = false,
            genders = listOf(
                GenderEntity(id = 1, name = "Macho", active = true),
                GenderEntity(id = 2, name = "Fêmea", active = true),
            )
        )
    )
}