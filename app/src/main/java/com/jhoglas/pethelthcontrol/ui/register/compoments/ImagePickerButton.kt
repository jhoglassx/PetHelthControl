package com.jhoglas.pethelthcontrol.ui.register.compoments

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImagePickerButton(
    onImagePicked: (Uri) -> Unit
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            onImagePicked(it)
        }
    }

    Button(
        modifier = Modifier
            .width(60.dp)
            .height(20.dp),
        shape = MaterialTheme.shapes.extraSmall,
        onClick = {
            launcher.launch("image/*")
        },
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            fontSize = 10.sp,
            text = "Add Image"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImagePickerButtonPreview() {
    ImagePickerButton(onImagePicked = {})
}