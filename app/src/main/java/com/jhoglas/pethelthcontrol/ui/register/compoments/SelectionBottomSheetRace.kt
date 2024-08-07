package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhoglas.domain.entity.RaceEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionBottomSheetRace(
    title: String,
    list: List<RaceEntity>,
    testTag: String,
    valueSelected: (RaceEntity) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = Modifier
            .testTag("${testTag}_BOTTOM_SHEET"),
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }
        },
        sheetState = sheetState
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            if (list.isEmpty()) {
                item {
                    Text(
                        text = "Nenhuma opção disponível",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                itemsIndexed(list) { index, value ->
                    Text(
                        text = value.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                valueSelected(value)
                                scope.launch { sheetState.hide() }
                            }
                            .padding(8.dp)
                            .testTag("${testTag}_BOTTOM_SHEET_$index")
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectionBottomSheetRacePreview() {
    MaterialTheme {
        SelectionBottomSheetRace(
            title = "Select Pet Race",
            list = listOf(
                RaceEntity(id = 1, name = "Vira-lata", active = true),
                RaceEntity(id = 2, name = "Tomba-lixo", active = true),
            ),
            valueSelected = {},
            testTag = "SelectionBottomSheetRace"
        )
    }
}