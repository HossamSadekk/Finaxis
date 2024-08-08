package core.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import core.navigation.nestedNavGraph.HomeRouteRoute
import core.navigation.nestedNavGraph.homeNavGraph
import core.navigation.nestedNavGraph.kyaNavGraph
import core.navigation.nestedNavGraph.navigateToHomeNavGraph
import core.navigation.nestedNavGraph.navigateToKycNavGraph
import core.utils.BottomBar
import presentation.home.nav.homeScreenRoute
import presentation.MoneyRequests.nav.moneyRequestsScreenRoute
import presentation.authScreen.nav.navigateToSignInpScreen
import presentation.authScreen.nav.navigateToSignUpScreen
import presentation.authScreen.nav.signInScreen
import presentation.authScreen.nav.signUpScreen
import presentation.passcode.nav.PasscodeScreenDest.HomeScreen
import presentation.passcode.nav.PasscodeScreenDest.KYC
import presentation.passcode.nav.navigateToPasscode
import presentation.passcode.nav.passcodeScreen
import presentation.passcode.nav.passcodeScreenRoute
import presentation.settings.nav.settingsScreenRoute
import presentation.splashScreen.nav.SplashScreenDest.PASSCODE
import presentation.splashScreen.nav.SplashScreenDest.WELCOME_SCREEN
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine the selected tab based on the current route
    val selectedTabIndex = when (currentRoute) {
        homeScreenRoute -> 0
        moneyRequestsScreenRoute -> 1
        settingsScreenRoute -> 2
        else -> -1
    }
    Scaffold(
        bottomBar = {
            if (selectedTabIndex != -1) {
                BottomBar(navController)
            }
        }
    ) {
        NavHost(navController = navController, startDestination = splashScreenRoute) {
            splashScreen { destination ->
                when (destination) {
                    WELCOME_SCREEN -> {
                        navController.navigateToWelcomeScreen()
                    }

                    PASSCODE -> {
                        navController.navigateToPasscode(fromSplashScreen = true)
                    }
                }
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

            signUpScreen(onBackPressed = {
                navController.navigateUp()
            }, onNextPressed = { phoneNumber, userName ->
                navController.navigateToPasscode(phoneNumber, userName, false)
            })
            signInScreen(
                onBackPressed = { navController.navigateUp() },
                onProceedClicked = { navController.navigateToPasscode(phoneNumber = it, fromSplashScreen = false) })
            passcodeScreen(
                onBackPressed = { navController.navigateUp() },
                onPasscodeSuccess = { destination ->
                    when (destination) {
                        HomeScreen -> {
                            navController.navigateToHomeNavGraph(passcodeScreenRoute)
                        }

                        KYC -> {
                            navController.navigateToKycNavGraph(startDestination = passcodeScreenRoute)
                        }
                    }
                })

            /** Nav Graph For KYC **/
            kyaNavGraph(navController = navController)

            /** Nav Graph For Home Screens **/
            homeNavGraph(navController = navController)
        }
    }
}//1234-5478-9312-3329