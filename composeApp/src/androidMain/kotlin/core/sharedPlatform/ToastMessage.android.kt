package core.sharedPlatform

import android.widget.Toast
import org.finaxis.app.MyApplication

actual fun showToast(message: String) {
    val context = MyApplication.instance
    context?.let {
        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
    }
}