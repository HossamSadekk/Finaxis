package core.sharedPlatform

import java.text.SimpleDateFormat
import java.util.Locale

actual fun formatDate(dateString: String): String {
    // Update the input format to match the provided date string
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yy", Locale.getDefault())
    return try {
        val date = inputFormat.parse(dateString)
        date?.let { outputFormat.format(it) } ?: ""
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}