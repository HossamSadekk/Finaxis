package presentation.settings.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.settings.SettingsScreen

const val settingsScreenRoute = "settingsScreenRoute"
fun NavGraphBuilder.settingsScreen() {
    composable(
        route = settingsScreenRoute,
    ) {
        SettingsScreen()
    }
}

