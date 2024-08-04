package core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import core.navigation.nestedNavGraph.kyaNavGraph
import core.navigation.nestedNavGraph.navigateToKycNavGraph
import presentation.authScreen.nav.navigateToSignInpScreen
import presentation.authScreen.nav.navigateToSignUpScreen
import presentation.authScreen.nav.signInScreen
import presentation.authScreen.nav.signUpScreen
import presentation.kyc_cardInfo.nav.cardInfoKycRoute
import presentation.kyc_cardInfo.nav.cardInfoKycScreen
import presentation.passcode.nav.navigateToPasscode
import presentation.passcode.nav.passcodeScreen
import presentation.splashScreen.nav.SplashScreenDest.KYC_INTRO
import presentation.splashScreen.nav.SplashScreenDest.WELCOME_SCREEN
import presentation.splashScreen.nav.splashScreen
import presentation.splashScreen.nav.splashScreenRoute
import presentation.welcomeScreen.nav.WelcomeScreenDest.SIGN_IN
import presentation.welcomeScreen.nav.WelcomeScreenDest.SIGN_UP
import presentation.welcomeScreen.nav.navigateToWelcomeScreen
import presentation.welcomeScreen.nav.welcomeScreen
import presentation.welcomeScreen.nav.welcomeScreenRoute

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = cardInfoKycRoute) {
        cardInfoKycScreen(onBackPressed = {
            navController.navigateUp()
        }, onProceedClicked = {

        })
        splashScreen { destination ->
            when (destination) {
                WELCOME_SCREEN -> {
                    navController.navigateToWelcomeScreen()
                }

                KYC_INTRO -> {
                    navController.navigateToKycNavGraph(startDestination = splashScreenRoute)
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
            navController.navigateToPasscode(phoneNumber, userName)
        })
        signInScreen {
            navController.navigateUp()
        }
        passcodeScreen(
            onBackPressed = { navController.navigateUp() },
            onPasscodeSuccess = { navController.navigateToKycNavGraph(startDestination = welcomeScreenRoute) })
        /** Nav Graph For KYC **/
        kyaNavGraph(navController = navController)
    }
}