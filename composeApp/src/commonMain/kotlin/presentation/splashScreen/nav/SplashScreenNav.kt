package presentation.splashScreen.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.splashScreen.SplashScreen

const val splashScreenRoute = "splashScreen"

fun NavGraphBuilder.splashScreen(onSplashEnd: () -> Unit) {
    composable(route = splashScreenRoute) {
        SplashScreen {
            onSplashEnd()
        }
    }
}
