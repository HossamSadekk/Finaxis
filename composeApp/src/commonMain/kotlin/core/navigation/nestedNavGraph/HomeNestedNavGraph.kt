package core.navigation.nestedNavGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import presentation.MoneyRequests.nav.RequestsScreen
import presentation.home.nav.homeScreen
import presentation.home.nav.homeScreenRoute
import presentation.send_receive_money.nav.moneyProcessRequest
import presentation.send_receive_money.nav.navigateToMoneyRequest
import presentation.settings.nav.settingsScreen
import presentation.username_money_request.nav.navigateToRequestProcess
import presentation.username_money_request.nav.requestProcessScreen

const val HomeRouteRoute = "HomeRoute"

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(startDestination = homeScreenRoute, route = HomeRouteRoute) {
        // bottom bar composables
        homeScreen(onRequestProcess = {
            navController.navigateToRequestProcess(it)
        })
        RequestsScreen()
        settingsScreen()
        // other composable
        requestProcessScreen(
            onBackPressed = { navController.navigateUp() },
            onProceedClicked = { request, username, note ->
                navController.navigateToMoneyRequest(request, username, note)
            })
        moneyProcessRequest(onBackPressed = { navController.navigateUp() })
    }
}

fun NavController.navigateToHomeNavGraph(destination: String) =
    this.navigate(HomeRouteRoute) {
        popUpTo(destination) { inclusive = true }
        launchSingleTop = true
    }

