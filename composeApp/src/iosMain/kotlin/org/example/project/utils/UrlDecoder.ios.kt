package org.example.project.utils

import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringByReplacingPercentEscapesUsingEncoding

actual fun decodeUrl(url: String): String {
    return (url as NSString).stringByReplacingPercentEscapesUsingEncoding(NSUTF8StringEncoding) ?: url
}