package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionBottomSheet(
    title: String,
    list: List<String>,
    valueSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }
        },
        sheetState = sheetState
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            list.forEach { value ->
                Text(
                    text = value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            valueSelected(value)
                            scope.launch { sheetState.hide() }
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun PreviewPetSexSelectionBottomSheet() {
    MaterialTheme {
        SelectionBottomSheet(
            title = "Select Pet Sex",
            list = listOf("value1", "value2", "value3"),
            valueSelected = {}
        )
    }
}