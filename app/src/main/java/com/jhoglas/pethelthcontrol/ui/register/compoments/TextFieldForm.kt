package com.jhoglas.pethelthcontrol.ui.register.compoments

import MaskVisualTransformation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextFieldForm(
    label: String,
    placeholder: String? = null,
    readOnly : Boolean = false,
    isLoading: Boolean = false,
    testTag: String,
    text: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }

    if(isLoading){
        Loading()
    } else {
        TextField(
            modifier =
            Modifier
                .fillMaxWidth()
                .testTag(testTag),
            label = {
                Text(
                    text = label,
                )
            },
            value = value,
            onValueChange = {
                value = it
                text(it)
            },
            maxLines = 1,
            readOnly = readOnly,
            visualTransformation = if (value.isEmpty() && placeholder != null)
                PlaceholderTransformation(placeholder)
            else VisualTransformation.None,
        )
    }
}

@Composable
fun TextFieldFormDate(
    label: String,
    placeholder: String? = null,
    readOnly : Boolean = false,
    isLoading: Boolean = false,
    testTag: String,
    date: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    val pattern = remember { Regex("^\\d+\$") }

    if(isLoading){
        Loading()
    } else {
        TextField(
            modifier =
            Modifier
                .fillMaxWidth()
                .testTag(testTag),
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
                    date(it)
                }
            },
            singleLine = true,
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation =  if (text.isEmpty() && placeholder != null)
                PlaceholderTransformation(placeholder)
            else MaskVisualTransformation("##/##/####"),
        )
    }
}

@Composable
fun TextFieldFormNumber(
    label: String,
    placeholder: String? = null,
    readOnly : Boolean = false,
    isLoading: Boolean = false,
    testTag: String,
    number: (Double) -> Unit
) {
    var number by remember { mutableStateOf("") }
    val pattern = remember { Regex("^\\d+\$") }

    if(isLoading){
        Loading()
    } else {
        TextField(
            modifier =
            Modifier
                .fillMaxWidth()
                .testTag(testTag),
            label = {
                Text(
                    text = label,
                )
            },
            value = number,
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    number = it
                    number(it.toDouble())
                }
            },
            singleLine = true,
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = if (number.isEmpty() && placeholder != null)
                PlaceholderTransformation(placeholder)
            else VisualTransformation.None,
        )
    }
}