package com.jhoglas.pethelthcontrol.ui.register.compoments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.jhoglas.pethelthcontrol.ui.ext.shimmerEffect

@Composable
fun Loading(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .shimmerEffect()
            .testTag("loading_field")
    )
}