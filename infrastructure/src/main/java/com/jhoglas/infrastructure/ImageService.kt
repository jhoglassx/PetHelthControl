package com.jhoglas.infrastructure

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ImageService(
    private val context: Context
) {
    fun toMultipartBodyPart(
        uri: Uri,
        partName: String,
        mediaType: String = "image/png"
    ): MultipartBody.Part {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "image.png")
        file.createNewFile()

        inputStream.use { input ->
            file.outputStream().use { output ->
                input?.copyTo(output)
            }
        }

        val requestFile = file.asRequestBody(mediaType.toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }
}
