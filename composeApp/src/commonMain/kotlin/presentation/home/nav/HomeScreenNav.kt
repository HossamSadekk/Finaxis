package presentation.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.home.HomeScreen

const val homeScreenRoute = "HomeScreen"
fun NavGraphBuilder.homeScreen(onRequestProcess: (Request) -> Unit) {
    composable(
        route = homeScreenRoute,
    ) {
        HomeScreen(onRequestProcess = {
            onRequestProcess(it)
        })
    }
}

enum class Request(val title: String, val message: String, val confirmButtonText: String) {
    SEND_MONEY("Send Money", "Are you sure you want to send money to this user?", "Send"),
    REQUEST_MONEY("Request Money", "Are you sure you want to request this amount of money?", "Request")
}