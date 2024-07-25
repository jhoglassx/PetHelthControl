package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun ReadonlyTextFieldFormRace(
    label: String,
    bottomSheetTitle: String,
    list: List<String>
){
    var showBottomSheet by remember { mutableStateOf(false) }
    var valueSelected by remember { mutableStateOf("") }

    ReadonlyTextField(
        value = TextFieldValue(
            text = valueSelected
        ) ,
        onValueChange = {},
        onClick = {
            showBottomSheet = true
        },
        label = {
            Text(
                text = label,
            )
        }
    )

    if(showBottomSheet){
        SelectionBottomSheet(
            title = bottomSheetTitle,
            list = list,
            valueSelected = {
                valueSelected = it
                showBottomSheet = false
            }
        )
    }
}