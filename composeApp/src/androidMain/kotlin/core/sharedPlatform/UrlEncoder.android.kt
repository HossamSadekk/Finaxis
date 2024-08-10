package core.sharedPlatform

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

actual fun encodeParam(param: String): String {
    return URLEncoder.encode(param, StandardCharsets.UTF_8.toString())
}