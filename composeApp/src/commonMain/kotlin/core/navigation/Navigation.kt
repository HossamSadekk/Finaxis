package core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import presentation.splashScreen.nav.splashScreen
import presentation.splashScreen.nav.splashScreenRoute

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = splashScreenRoute) {

        splashScreen {

        }
    }
}