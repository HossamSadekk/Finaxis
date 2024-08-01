package presentation.kyc.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.authScreen.nav.signUpScreenRoute
import presentation.kyc.KycIntroScreen

const val KycScreenRoute = "kycRoute"
const val KycIntroScreenRoute = "kycIntroScreenRoute"

fun NavGraphBuilder.kycIntroScreen(onProceedPressed: () -> Unit) {
    slideComposable(route = KycIntroScreenRoute) {
        KycIntroScreen {
            onProceedPressed()
        }
    }
}
fun NavController.navigateToSignUpScreen() =
    this.navigate(signUpScreenRoute)
