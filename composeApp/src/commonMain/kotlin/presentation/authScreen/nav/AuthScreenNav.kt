package presentation.authScreen.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.authScreen.SignInScreen
import presentation.authScreen.SignUpScreen

const val signUpScreenRoute = "signupScreen"
const val signInScreenRoute = "signInpScreen"

fun NavGraphBuilder.signUpScreen(onBackPressed: () -> Unit) {
    slideComposable(route = signUpScreenRoute) {
        SignUpScreen {
            onBackPressed()
        }
    }
}
fun NavGraphBuilder.signInScreen(onBackPressed: () -> Unit) {
    slideComposable(route = signInScreenRoute) {
        SignInScreen {
            onBackPressed()
        }
    }
}
fun NavController.navigateToSignUpScreen() =
    this.navigate(signUpScreenRoute)

fun NavController.navigateToSignInpScreen() =
    this.navigate(signInScreenRoute)
