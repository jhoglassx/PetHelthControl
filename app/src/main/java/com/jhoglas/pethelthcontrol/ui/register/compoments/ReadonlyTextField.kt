package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ReadonlyTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    isLoading: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if(isLoading){
            Loading()
        } else {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                label = label
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = onClick),
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ReadonlyTextFieldPreview() {
    ReadonlyTextField(
        value = TextFieldValue(""),
        onValueChange = {},
        onClick = {},
        label = {
            Text(text = "Label")
        },
        isLoading = true
    )
}