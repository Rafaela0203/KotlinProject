package org.example.project.utils

import java.net.URLDecoder

actual fun decodeUrl(url: String): String {
    return URLDecoder.decode(url, "UTF-8")
}