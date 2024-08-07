package presentation.authScreen.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.authScreen.signin.SignInScreen
import presentation.authScreen.signup.SignUpScreen

const val signUpScreenRoute = "signupScreen"
const val signInScreenRoute = "signInpScreen"

fun NavGraphBuilder.signUpScreen(onBackPressed: () -> Unit, onNextPressed: (String, String) -> Unit) {
    slideComposable(route = signUpScreenRoute) {
        SignUpScreen(onBackPressed = {
            onBackPressed()
        }, onNextPressed = { phoneNumber, userName ->
            onNextPressed(phoneNumber, userName)
        })
    }
}

fun NavGraphBuilder.signInScreen(onBackPressed: () -> Unit, onProceedClicked: (String) -> Unit) {
    slideComposable(route = signInScreenRoute) {
        SignInScreen(onBackPressed = { onBackPressed() }, onProceedClicked = {
            onProceedClicked(it)
        })
    }
}

fun NavController.navigateToSignUpScreen() =
    this.navigate(signUpScreenRoute)

fun NavController.navigateToSignInpScreen() =
    this.navigate(signInScreenRoute)
