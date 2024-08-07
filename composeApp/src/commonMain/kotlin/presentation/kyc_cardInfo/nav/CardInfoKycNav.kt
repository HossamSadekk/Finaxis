package presentation.kyc_cardInfo.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.kyc_cardInfo.CardInfoKyc

const val cardInfoKycRoute = "cardInfoKyScreen"

fun NavGraphBuilder.cardInfoKycScreen(onBackPressed: () -> Unit, onProceedClicked: () -> Unit) {
    composable(route = cardInfoKycRoute) {
        CardInfoKyc(onBackPressed = { onBackPressed() }, onProceed = { onProceedClicked() })
    }
}

fun NavController.navigateToCardInfoKyc() =
    this.navigate(cardInfoKycRoute)
