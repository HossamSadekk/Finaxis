package presentation.splashScreen.nav

import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.splashScreen.SplashScreen

const val splashScreenRoute = "splashScreen"

fun NavGraphBuilder.splashScreen(onNavigation: (SplashScreenDest) -> Unit) {
    slideComposable(route = splashScreenRoute) {
        SplashScreen { destination ->
            onNavigation(destination)
        }
    }
}

enum class SplashScreenDest {
    WELCOME_SCREEN,
    KYC_INTRO
}