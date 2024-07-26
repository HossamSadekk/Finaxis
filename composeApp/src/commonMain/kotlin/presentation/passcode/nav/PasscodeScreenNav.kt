package presentation.passcode.nav

import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.passcode.PasscodeScreen

const val passcodeScreenRoute = "passcodeScreen"

fun NavGraphBuilder.passcodeScreen() {
    slideComposable(route = passcodeScreenRoute) {
        PasscodeScreen()
    }
}
