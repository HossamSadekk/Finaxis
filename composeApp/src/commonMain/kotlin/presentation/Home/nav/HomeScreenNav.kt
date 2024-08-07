package presentation.Home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.navigation.transitionNavigation.slideComposable
import presentation.Home.HomeScreen

const val homeScreenRoute = "HomeScreen"
fun NavGraphBuilder.homeScreen() {
    composable(
        route = homeScreenRoute,
    ) {
        HomeScreen()
    }
}
