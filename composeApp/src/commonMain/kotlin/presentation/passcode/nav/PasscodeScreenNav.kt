package presentation.passcode.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.navigation.transitionNavigation.slideComposable
import presentation.passcode.PasscodeScreen
import presentation.splashScreen.nav.splashScreenRoute

const val passcodeScreenRoute = "passcode/{phoneNumber}/{username}"

fun NavGraphBuilder.passcodeScreen(onBackPressed: () -> Unit, onPasscodeSuccess: (PasscodeScreenDest) -> Unit) {
    composable(route = passcodeScreenRoute) {
        val phoneNumber = it.arguments?.getString("phoneNumber")
        val username = it.arguments?.getString("username")
        PasscodeScreen(
            phoneNumber = phoneNumber,
            username = username,
            onBackPressed = onBackPressed,
        ) { destination ->
            onPasscodeSuccess(destination)
        }
    }
}

fun NavController.navigateToPasscode(phoneNumber: String? = null, username: String? = null, fromSplashScreen: Boolean) {
    this.navigate("passcode/$phoneNumber/$username") {
        if (fromSplashScreen) {
            popUpTo(splashScreenRoute) { inclusive = true }
        }
    }
}

enum class PasscodeScreenDest {
    HomeScreen,
    KYC
}