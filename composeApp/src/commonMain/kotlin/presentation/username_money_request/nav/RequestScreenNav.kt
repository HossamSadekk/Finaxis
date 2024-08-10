package presentation.username_money_request.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import presentation.home.nav.Request
import presentation.username_money_request.RequestScreen

const val requestProcessRoute = "requestProcessRoute/{requestType}"

fun NavGraphBuilder.requestProcessScreen(
    onBackPressed: () -> Unit,
    onProceedClicked: (Request, String, String) -> Unit,
) {
    composable(
        route = requestProcessRoute,
        arguments = listOf(navArgument("requestType") { type = NavType.StringType })
    ) { backStackEntry ->
        val requestTypeString = backStackEntry.arguments?.getString("requestType")
        val requestType = Request.valueOf(requestTypeString ?: Request.SEND_MONEY.name)
        RequestScreen(
            request = requestType,
            onBackPressed = { onBackPressed() },
            onProceed = { request, username, note ->
                onProceedClicked(request, username, note)
            }
        )
    }
}

fun NavController.navigateToRequestProcess(requestType: Request) =
    this.navigate("requestProcessRoute/${requestType.name}")