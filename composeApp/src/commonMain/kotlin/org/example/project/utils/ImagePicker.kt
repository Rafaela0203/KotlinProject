package org.example.project.utils

import androidx.compose.runtime.Composable

@Composable
expect fun ImagePicker(
    show: Boolean,
    onImageSelected: (imagePath: String?) -> Unit
)