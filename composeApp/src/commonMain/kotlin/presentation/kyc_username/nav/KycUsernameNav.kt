package presentation.kyc_username.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.kyc_username.KycUsernameScreen

const val kycUsernameRoute = "kycUsernameScreen"

fun NavGraphBuilder.kycUsernameScreen(onBackPressed: () -> Unit, onProceedClicked: () -> Unit) {
    slideComposable(route = kycUsernameRoute) {
        KycUsernameScreen(onBackPressed = { onBackPressed() }, onProceedClicked = { onProceedClicked() })
    }
}

fun NavController.navigateToKycUsernameScreen() =
    this.navigate(kycUsernameRoute)
