package presentation.Bank.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.navigation.transitionNavigation.slideComposable
import presentation.Bank.BankSelectionScreen

const val bankSelectionScreenRoute = "bankSelectionScreen"

fun NavGraphBuilder.bankSelectionScreen(onBackPressed: () -> Unit) {
    slideComposable(route = bankSelectionScreenRoute) {
        BankSelectionScreen {
            onBackPressed()
        }
    }
}

fun NavController.navigateToBankSelectionScreen() =
    this.navigate(bankSelectionScreenRoute)
