package presentation.splashScreen.nav

import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.splashScreen.SplashScreen

const val splashScreenRoute = "splashScreen"

fun NavGraphBuilder.splashScreen(onSplashEnd: () -> Unit) {
    slideComposable(route = splashScreenRoute) {
        SplashScreen {
            onSplashEnd()
        }
    }
}
