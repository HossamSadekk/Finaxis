package core.navigation.nestedNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import presentation.kyc.nav.KycIntroScreenRoute
import presentation.kyc.nav.KycScreenRoute
import presentation.kyc.nav.kycIntroScreen
import presentation.splashScreen.nav.splashScreenRoute

fun NavGraphBuilder.kyaNavGraph(navController: NavHostController) {
    navigation(startDestination = KycIntroScreenRoute, route = KycScreenRoute) {
        kycIntroScreen {

        }
    }
}

fun NavController.navigateToKycNavGraph() =
    this.navigate(KycScreenRoute) { popUpTo(splashScreenRoute) { inclusive = true } }

