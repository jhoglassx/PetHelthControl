package com.jhoglas.pethelthcontrol.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jhoglas.pethelthcontrol.ui.register.compoments.OutLinedTextFieldForm
import com.jhoglas.pethelthcontrol.ui.register.compoments.OutLinedTextFieldFormDate
import com.jhoglas.pethelthcontrol.ui.register.compoments.OutLinedTextFieldFormNumber
import com.jhoglas.pethelthcontrol.ui.register.compoments.ReadonlyTextFieldFormRace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Pet Health Control") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->

        val raceList = listOf("Vira-Lata", "Poddle", "Box")
        val sexList = listOf("Male", "Female")

        Column(
            modifier =
            Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(120.dp)
                        .height(120.dp)
                        .background(Color.Blue),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(Color.Black),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Image(
                            imageVector = Icons.Filled.Face,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentDescription = "Pet Picture",
                            colorFilter = ColorFilter.tint(Color.White),
                        )

                        Text(
                            text = "Pet Picture",
                            color = Color.White,
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutLinedTextFieldForm(
                        label = "Pet Name: ",
                        placeholder = "Enter your pet's name"
                    )
                    OutLinedTextFieldForm(
                        label = "Pet Surname: ",
                        placeholder = "Enter your pet's name"
                    )
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    ReadonlyTextFieldFormRace(
                        label = "Sex: ",
                        bottomSheetTitle = "Sex: ",
                        list = sexList
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column (
                    modifier = Modifier
                        .weight(1f),
                ){
                    ReadonlyTextFieldFormRace(
                        label = "Race: ",
                        bottomSheetTitle = "Race: ",
                        list = raceList
                    )
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(){
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    OutLinedTextFieldFormDate(
                        label = "Pet Age: ",
                        placeholder = "01/01/2021",
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    OutLinedTextFieldFormNumber(
                        label = "Pet Weight: ",
                        placeholder = "00.00",
                    )
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(){
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    OutLinedTextFieldForm(
                        label = "Hair Color: ",
                        placeholder = "Black",
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    OutLinedTextFieldForm(
                        label = "Hair Type: ",
                        placeholder = "short",
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
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        navController = rememberNavController()
    )
}
