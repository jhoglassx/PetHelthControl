package com.jhoglas.pethelthcontrol.ui.register

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
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
                uiState = uiState
            )
        }
    }
}

@Composable
fun RegisterContent(
    uiState: RegisterState,
) {
    Column(
        modifier =
        Modifier
            .padding(4.dp)
            .fillMaxSize(),
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
                    contentAlignment = Alignment.TopStart
                ) {
                    Image(
                        imageVector = Icons.Filled.Face,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentDescription = "Pet Picture",
                        colorFilter = ColorFilter.tint(Color.White),
                    )
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
                        isLoading = uiState.isLoading
                    )
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
                        isLoading = uiState.isLoading
                    )
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
                    label = "Gender: ",
                    bottomSheetTitle = "Gender: ",
                    uiState = uiState
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column (
                modifier = Modifier
                    .weight(1f),
            ){
                ReadonlyTextFieldFormRace(
                    label = "Race: ",
                    bottomSheetTitle = "Race: ",
                    uiState = uiState
                )
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
                    isLoading = uiState.isLoading
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                TextFieldFormNumber(
                    label = "Pet Weight: ",
                    placeholder = "00.00",
                    isLoading = uiState.isLoading
                )
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
                    isLoading = uiState.isLoading
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                TextFieldForm(
                    label = "Hair Type: ",
                    placeholder = "short",
                    isLoading = uiState.isLoading
                )
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(8.dp)
            ){
                Text(text = "Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterContent(
        uiState = RegisterState()
    )
}
