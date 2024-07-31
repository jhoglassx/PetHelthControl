package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.jhoglas.pethelthcontrol.ui.ext.shimmerEffect

@Composable
fun PetImage (
    img: String,
    isLoading: Boolean = false
){
    if(isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmerEffect()
        )
    } else{
        AsyncImage(
            model = img,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth().aspectRatio(1f)
                .shimmerEffect(),
            contentDescription = "Pet Picture"
        )
    }


}