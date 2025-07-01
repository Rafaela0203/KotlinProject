package org.example.project.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.darwin.NSObject

@Composable
actual fun ImagePicker(
    show: Boolean,
    onImageSelected: (imagePath: String?) -> Unit
) {
    if (show) {
        println("Image Picker no iOS precisa de uma implementação nativa mais detalhada.")
        onImageSelected(null)
    }
}