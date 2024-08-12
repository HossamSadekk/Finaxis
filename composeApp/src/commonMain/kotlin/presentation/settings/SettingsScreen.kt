package presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import core.designSystem.theme.DpDimensions
import core.sharedPlatform.PlatformColors
import core.sharedPlatform.showToast
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.ic_about
import finaxis.composeapp.generated.resources.ic_help
import finaxis.composeapp.generated.resources.ic_logout
import finaxis.composeapp.generated.resources.ic_person
import finaxis.composeapp.generated.resources.ic_privacy
import finaxis.composeapp.generated.resources.ic_security
import finaxis.composeapp.generated.resources.poppins_regular
import org.jetbrains.compose.resources.Font
import presentation.settings.components.SettingOption
import presentation.settings.components.SettingsItem

@Composable
fun SettingsScreen() {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    val settingsItems = listOf(
        SettingOption("Account", Res.drawable.ic_person, MaterialTheme.colorScheme.onPrimary),
        SettingOption("Privacy", Res.drawable.ic_privacy, MaterialTheme.colorScheme.onPrimary),
        SettingOption("Security", Res.drawable.ic_security, MaterialTheme.colorScheme.onPrimary),
        SettingOption("Help Center", Res.drawable.ic_help, MaterialTheme.colorScheme.onPrimary),
        SettingOption("About", Res.drawable.ic_about, MaterialTheme.colorScheme.onPrimary),
        SettingOption("Logout", Res.drawable.ic_logout, MaterialTheme.colorScheme.error)
    )
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header text
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineLarge.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(16.dp)
                    .paddingFromBaseline(top = 24.dp) // Adjust padding from the baseline
            )

            // Settings items
            LazyColumn(
                modifier = Modifier
                    .padding(DpDimensions.Normal),
                verticalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(settingsItems) { item ->
                    SettingsItem(
                        title = item.title,
                        icon = item.icon,
                        iconTint = item.iconTint,
                        onClick = {
                            showToast("Under Development")
                        }
                    )
                }
            }
        }
    }
}

