package org.example.project.utils

import java.net.URLEncoder

actual fun encodeUrl(url: String): String {
    return URLEncoder.encode(url, "UTF-8").replace("+", "%20")
}