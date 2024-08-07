package core.navigation.nestedNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import presentation.Home.nav.homeScreen
import presentation.Home.nav.homeScreenRoute
import presentation.MoneyRequests.nav.moneyRequestsScreen
import presentation.passcode.nav.passcodeScreenRoute
import presentation.settings.nav.settingsScreen

const val HomeRouteRoute = "HomeRoute"

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(startDestination = homeScreenRoute, route = HomeRouteRoute) {
        homeScreen()
        moneyRequestsScreen()
        settingsScreen()
    }
}

fun NavController.navigateToHomeNavGraph(destination: String) =
    this.navigate(HomeRouteRoute) {
        popUpTo(destination) { inclusive = true }
        launchSingleTop = true
    }

// 1234-5478-9312-3326