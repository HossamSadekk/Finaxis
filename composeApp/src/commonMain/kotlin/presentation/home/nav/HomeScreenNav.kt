package presentation.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.home.HomeScreen

const val homeScreenRoute = "HomeScreen"
fun NavGraphBuilder.homeScreen() {
    composable(
        route = homeScreenRoute,
    ) {
        HomeScreen()
    }
}
