package core.sharedPlatform

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.localTimeZone

actual fun formatDate(dateString: String): String {
    val possibleFormats = listOf(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
        "yyyy-MM-dd'T'HH:mm:ss.SSS",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd HH:mm:ss.SSSSSS",
        "yyyy-MM-dd HH:mm:ss.SSS",
        "yyyy-MM-dd HH:mm:ss"
    )

    val outputFormatter = NSDateFormatter().apply {
        dateFormat = "dd MMM yy"
        locale = NSLocale.currentLocale
        timeZone = NSTimeZone.localTimeZone
    }

    for (format in possibleFormats) {
        val inputFormatter = NSDateFormatter().apply {
            dateFormat = format
            locale = NSLocale.currentLocale
            timeZone = NSTimeZone.localTimeZone
        }

        val date = inputFormatter.dateFromString(dateString)
        if (date != null) {
            return outputFormatter.stringFromDate(date)
        }
    }

    return ""
}

