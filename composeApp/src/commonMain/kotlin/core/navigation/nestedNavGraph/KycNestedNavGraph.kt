package core.navigation.nestedNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import presentation.Bank.nav.bankSelectionScreen
import presentation.Bank.nav.navigateToBankSelectionScreen
import presentation.kyc.nav.KycIntroScreenRoute
import presentation.kyc.nav.KycScreenRoute
import presentation.kyc.nav.kycIntroScreen

fun NavGraphBuilder.kyaNavGraph(navController: NavController) {
    navigation(startDestination = KycIntroScreenRoute, route = KycScreenRoute) {
        kycIntroScreen {
            navController.navigateToBankSelectionScreen()
        }
        bankSelectionScreen(onBackPressed = {
            navController.navigateUp()
        })
    }
}

fun NavController.navigateToKycNavGraph(startDestination: String) =
    this.navigate(KycScreenRoute) {
        popUpTo(startDestination) { inclusive = true }
        launchSingleTop = true
    }

//fun NavController.navigateToKyc() =
//    this.navigate(KycIntroScreenRoute) {
//        popUpTo(welcomeScreenRoute) { inclusive = true }
//        launchSingleTop = true
//    }