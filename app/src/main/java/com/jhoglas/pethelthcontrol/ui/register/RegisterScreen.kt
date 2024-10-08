package com.jhoglas.pethelthcontrol.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jhoglas.domain.entity.PetEntity
import com.jhoglas.pethelthcontrol.ui.register.compoments.PetImage
import com.jhoglas.pethelthcontrol.ui.register.compoments.ReadonlyTextFieldFormGender
import com.jhoglas.pethelthcontrol.ui.register.compoments.ReadonlyTextFieldFormRace
import com.jhoglas.pethelthcontrol.ui.register.compoments.RegisterTopBar
import com.jhoglas.pethelthcontrol.ui.register.compoments.TextFieldForm
import com.jhoglas.pethelthcontrol.ui.register.compoments.TextFieldFormDate
import com.jhoglas.pethelthcontrol.ui.register.compoments.TextFieldFormNumber
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterAction
import com.jhoglas.pethelthcontrol.ui.register.model.RegisterState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController
) {

    val viewModel: RegisterViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(RegisterAction.LoadGenders)
        viewModel.onAction(RegisterAction.LoadRaces)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { RegisterTopBar(navController) }
    ) { innerPadding ->
        Surface(
            color = Color.White,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            RegisterContent(
                uiState = uiState,
                onAction = viewModel::onAction
            )
        }
    }
}

@Composable
fun RegisterContent(
    uiState: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    var pet by remember { mutableStateOf<PetEntity?>(PetEntity()) }

    Column(
        modifier =
        Modifier
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .size(119.dp)
                    .background(Color.Blue),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.TopStart,
                ) {
                    PetImage(
                        isLoading = uiState.isLoading,
                    ){
                        pet = pet?.copy(
                            image = it
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ) {
                    TextFieldForm(
                        label = "Pet Name: ",
                        placeholder = "Enter your pet's name",
                        isLoading = uiState.isLoading,
                        testTag = "PET_NAME"
                    ) { name ->
                        pet = pet?.copy(name = name)
                        onAction(RegisterAction.EnableSaveButton(pet))
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ) {
                    TextFieldForm(
                        label = "Pet Surname: ",
                        placeholder = "Enter your pet's name",
                        isLoading = uiState.isLoading,
                        testTag = "PET_SURNAME"
                    ){ surname ->
                        pet = pet?.copy(surname = surname)
                        onAction(RegisterAction.EnableSaveButton(pet))
                    }
                }

            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                ReadonlyTextFieldFormGender(
                    uiState = uiState,
                    testTag = "PET_GENDER"
                ){ gender ->
                    pet = pet?.copy(gender = gender)
                    onAction(RegisterAction.EnableSaveButton(pet))
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column (
                modifier = Modifier
                    .weight(1f),
            ){
                ReadonlyTextFieldFormRace(
                    uiState = uiState,
                    testTag = "PET_RACE"
                ){ race ->
                    pet = pet?.copy(race = race)
                    onAction(RegisterAction.EnableSaveButton(pet))
                }
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
        ){
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                TextFieldFormDate(
                    label = "Pet Age: ",
                    placeholder = "01/01/2021",
                    isLoading = uiState.isLoading,
                    testTag = "PET_DATE"
                ){ dateBorn ->
                    pet = pet?.copy(dateBorn = dateBorn)
                    onAction(RegisterAction.EnableSaveButton(pet))
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                TextFieldFormNumber(
                    label = "Pet Weight: ",
                    placeholder = "00.00",
                    isLoading = uiState.isLoading,
                    testTag = "PET_WEIGHT"
                ){ weight ->
                    pet = pet?.copy(weight = weight)
                    onAction(RegisterAction.EnableSaveButton(pet))
                }
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ){
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                TextFieldForm(
                    label = "Hair Color: ",
                    placeholder = "Black",
                    isLoading = uiState.isLoading,
                    testTag = "PET_HAIR_COLOR"
                ){ hairColor ->
                    pet = pet?.copy(
                        hair = pet?.hair?.copy(
                            color = hairColor
                        )
                    )
                    onAction(RegisterAction.EnableSaveButton(pet))
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                TextFieldForm(
                    label = "Hair Type: ",
                    placeholder = "short",
                    isLoading = uiState.isLoading,
                    testTag = "PET_HAIR_TYPE"
                ){ hairType ->
                    pet = pet?.copy(
                        hair = pet?.hair?.copy(
                            type = hairType
                        )
                    )
                    onAction(RegisterAction.EnableSaveButton(pet))
                }
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier
                    .testTag("SAVE_BUTTON"),
                onClick = {
                    onAction(RegisterAction.SavePet(pet))
                },
                shape = MaterialTheme.shapes.small,
                enabled = uiState.saveButtonIsEnabled
            ){
                Text(
                    modifier = Modifier
                        .padding(4.dp),
                    text = "Save"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterContent(
        uiState = RegisterState(),
        onAction = {}
    )
}
