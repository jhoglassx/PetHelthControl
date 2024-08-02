package com.jhoglas.pethelthcontrol.ui.register.compoments

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.jhoglas.pethelthcontrol.ui.ext.shimmerEffect

@Composable
fun PetImage (
    isLoading: Boolean = false,
    image:(Uri) -> Unit
){

    var imageLocal by remember {
        mutableStateOf<Uri>(
            Uri.parse("https://petshopdamadre.com.br/wp-content/uploads/2022/11/123.jpg")
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageLocal = it
            image(it)
        }
    }

    if(isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmerEffect()
        )
    } else{
        AsyncImage(
            model = imageLocal,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth().aspectRatio(1f)
                .clickable {
                    launcher.launch("image/*")
               },
            contentDescription = "Pet Picture"
        )
    }
}