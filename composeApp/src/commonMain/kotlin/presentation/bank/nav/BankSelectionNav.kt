package presentation.bank.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.bank.BankSelectionScreen

const val bankSelectionScreenRoute = "bankSelectionScreen"

fun NavGraphBuilder.bankSelectionScreen(onBackPressed: () -> Unit, onProceedClicked: () -> Unit) {
    slideComposable(route = bankSelectionScreenRoute) {
        BankSelectionScreen(onBackPressed = { onBackPressed() }, onProceedSuccess = { onProceedClicked() })
    }
}

fun NavController.navigateToBankSelectionScreen() =
    this.navigate(bankSelectionScreenRoute)
