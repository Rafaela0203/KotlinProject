package org.example.project.utils

import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters

actual fun encodeUrl(url: String): String {
    return (url as NSString).stringByAddingPercentEncodingWithAllowedCharacters(
        allowedCharacters = NSCharacterSet.URLQueryAllowedCharacterSet
            ()) ?: url
}