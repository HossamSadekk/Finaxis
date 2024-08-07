package presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import core.sharedPlatform.PlatformColors
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_light
import org.jetbrains.compose.resources.Font

@Composable
fun SettingsScreen() {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Setting Screen",
            style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(resource = font.poppins_light))),
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}