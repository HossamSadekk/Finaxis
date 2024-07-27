package core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import presentation.authScreen.nav.navigateToSignInpScreen
import presentation.authScreen.nav.navigateToSignUpScreen
import presentation.authScreen.nav.signInScreen
import presentation.authScreen.nav.signUpScreen
import presentation.splashScreen.nav.splashScreen
import presentation.splashScreen.nav.splashScreenRoute
import presentation.welcomeScreen.nav.WelcomeScreenDest.SIGN_IN
import presentation.welcomeScreen.nav.WelcomeScreenDest.SIGN_UP
import presentation.welcomeScreen.nav.navigateToWelcomeScreen
import presentation.welcomeScreen.nav.welcomeScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = splashScreenRoute) {

        splashScreen {
            navController.navigateToWelcomeScreen()
        }
        welcomeScreen { destination ->
            when (destination) {
                SIGN_IN -> {
                    navController.navigateToSignInpScreen()
                }

                SIGN_UP -> {
                    navController.navigateToSignUpScreen()
                }
            }
        }

        signUpScreen {
            navController.navigateUp()
        }
        signInScreen {
            navController.navigateUp()
        }
    }
}