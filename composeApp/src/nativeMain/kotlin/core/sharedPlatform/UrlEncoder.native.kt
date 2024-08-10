package core.sharedPlatform
// iosMain/src/iosMain/kotlin/UrlEncoder.kt
import platform.Foundation.NSCharacterSet
import platform.Foundation.NSMutableCharacterSet
import platform.Foundation.NSString
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters

actual fun encodeParam(param: String): String {
    val nsString = param as NSString
    val allowedCharacterSet = NSCharacterSet.alphanumericCharacterSet().mutableCopy() as NSMutableCharacterSet
    allowedCharacterSet.addCharactersInString("-._~")

    return nsString.stringByAddingPercentEncodingWithAllowedCharacters(allowedCharacterSet) ?: param
}
