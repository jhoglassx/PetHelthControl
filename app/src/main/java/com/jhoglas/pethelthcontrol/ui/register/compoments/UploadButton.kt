package com.jhoglas.pethelthcontrol.ui.register.compoments

import android.content.Context
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.io.File
import java.io.IOException

@Composable
fun UploadButton(
    context: Context,
    imageUri: Uri,
    onUpload: (File) -> Unit
) {
    Button(onClick = {
        try {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val file = File(context.cacheDir, "image.png")
            file.createNewFile()
            inputStream?.use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            onUpload(file)
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle error
        }
    }) {
        Text(text = "Upload")
    }
}