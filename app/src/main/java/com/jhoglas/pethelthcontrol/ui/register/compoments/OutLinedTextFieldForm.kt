package com.jhoglas.pethelthcontrol.ui.register.compoments

import MaskVisualTransformation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OutLinedTextFieldForm(
    label: String,
    placeholder: String? = null,
    readOnly : Boolean = false,
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier =
        Modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
            )
        },
        value = text,
        placeholder = {
            Text(
                text = placeholder ?: "",
            )
        },
        onValueChange = {
            text = it
        },
        maxLines = 1,
        readOnly = readOnly,
    )
}

@Composable
fun OutLinedTextFieldFormDate(
    label: String,
    placeholder: String? = null,
    readOnly : Boolean = false,
) {
    var text by remember { mutableStateOf("") }

    val pattern = remember { Regex("^\\d+\$") }

    OutlinedTextField(
        modifier =
        Modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
            )
        },
        placeholder = {
            Text(
                text = placeholder ?: "",
            )
        },
        value = text,
        onValueChange = {
            if (it.isEmpty() || it.matches(pattern)) {
                text = it
            }
        },
        singleLine = true,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskVisualTransformation("##/##/####"),
    )
}

@Composable
fun OutLinedTextFieldFormNumber(
    label: String,
    placeholder: String? = null,
    readOnly : Boolean = false,
) {
    var text by remember { mutableStateOf("") }

    val pattern = remember { Regex("^\\d+\$") }

    OutlinedTextField(
        modifier =
        Modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
            )
        },
        placeholder = {
            Text(
                text = placeholder ?: "",
            )
        },
        value = text,
        onValueChange = {
            if (it.isEmpty() || it.matches(pattern)) {
                text = it
            }
        },
        singleLine = true,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskVisualTransformation(""),
    )
}