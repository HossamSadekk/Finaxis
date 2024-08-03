package presentation.kyc_phoneNumber.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.kyc_phoneNumber.PhoneNumberKycScreen

const val phoneNumberKycRoute = "phoneNumberKycScreen"

fun NavGraphBuilder.phoneNumberKycScreen(onBackPressed: () -> Unit, onProceedClicked: () -> Unit) {
    slideComposable(route = phoneNumberKycRoute) {
        PhoneNumberKycScreen(onBackPressed = { onBackPressed() }, onProceed = { onProceedClicked() })
    }
}

fun NavController.navigateToPhoneNumberKycScreen() =
    this.navigate(phoneNumberKycRoute)
