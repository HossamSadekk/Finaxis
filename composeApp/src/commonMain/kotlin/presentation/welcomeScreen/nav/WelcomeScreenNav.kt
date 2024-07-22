package presentation.welcomeScreen.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.splashScreen.nav.splashScreenRoute
import presentation.welcomeScreen.WelcomeScreen

const val welcomeScreenRoute = "welcomeScreen"

fun NavGraphBuilder.welcomeScreen(onNavigation: (WelcomeScreenDest) -> Unit) {
    slideComposable(
        route = welcomeScreenRoute,
    ) {
        WelcomeScreen(onNavigation = {
            onNavigation(it)
        })
    }
}

fun NavController.navigateToWelcomeScreen() =
    this.navigate(welcomeScreenRoute) { popUpTo(splashScreenRoute) { inclusive = true } }

enum class WelcomeScreenDest {
    SIGN_IN,
    SIGN_UP
}