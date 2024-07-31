package presentation.passcode.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.passcode.PasscodeScreen

const val passcodeScreenRoute = "passcode/{phoneNumber}/{username}"

fun NavGraphBuilder.passcodeScreen(onBackPressed: () -> Unit) {
    slideComposable(route = passcodeScreenRoute) {
        val phoneNumber = it.arguments?.getString("phoneNumber")
        val username = it.arguments?.getString("username")
        PasscodeScreen(
            phoneNumber = phoneNumber,
            username = username,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToPasscode(phoneNumber: String, username: String) {
    this.navigate("passcode/$phoneNumber/$username")
}