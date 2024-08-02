package core.navigation.nestedNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import presentation.kyc.nav.KycIntroScreenRoute
import presentation.kyc.nav.KycScreenRoute
import presentation.kyc.nav.kycIntroScreen
import presentation.welcomeScreen.nav.welcomeScreenRoute

fun NavGraphBuilder.kyaNavGraph() {
    navigation(startDestination = KycIntroScreenRoute, route = KycScreenRoute) {
        kycIntroScreen {

        }
    }
}

fun NavController.navigateToKycNavGraph() =
    this.navigate(KycScreenRoute) {
        popUpTo(welcomeScreenRoute) { inclusive = true }
        launchSingleTop = true
    }

//fun NavController.navigateToKyc() =
//    this.navigate(KycIntroScreenRoute) {
//        popUpTo(welcomeScreenRoute) { inclusive = true }
//        launchSingleTop = true
//    }