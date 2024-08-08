package core.sharedPlatform

import platform.Foundation.NSDateFormatter

actual fun formatDate(dateString: String): String {
    val inputFormatter = NSDateFormatter().apply {
        dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
    }

    val outputFormatter = NSDateFormatter().apply {
        dateFormat = "dd MMM yy"
    }

    val date = inputFormatter.dateFromString(dateString)
    return date?.let { outputFormatter.stringFromDate(it) } ?: ""
}
