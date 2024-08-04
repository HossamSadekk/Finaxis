package presentation.kyc_cardInfo.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.kyc_cardInfo.CardInfoKyc

const val cardInfoKycRoute = "cardInfoKyccreen"

fun NavGraphBuilder.cardInfoKycScreen(onBackPressed: () -> Unit, onProceedClicked: () -> Unit) {
    slideComposable(route = cardInfoKycRoute) {
        CardInfoKyc(onBackPressed = { onBackPressed() }, onProceed = { onProceedClicked() })
    }
}

fun NavController.navigateToCardInfoKyc() =
    this.navigate(cardInfoKycRoute)
