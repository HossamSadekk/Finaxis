package presentation.send_receive_money.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import presentation.home.nav.Request
import presentation.send_receive_money.MoneyRequest

const val moneyRequestRoute = "moneyRequestRoute/{requestType}/{username}/{note}"
fun NavGraphBuilder.moneyProcessRequest(onBackPressed: () -> Unit) {
    composable(
        route = moneyRequestRoute,
        arguments = listOf(
            navArgument("requestType") { type = NavType.StringType },
            navArgument("username") { type = NavType.StringType },
            navArgument("note") { type = NavType.StringType; defaultValue = "" }
        )
    ) { backStackEntry ->
        val requestTypeString = backStackEntry.arguments?.getString("requestType")
        val requestType = Request.valueOf(requestTypeString ?: "")

        val username = backStackEntry.arguments?.getString("username") ?: ""
        val note = backStackEntry.arguments?.getString("note") ?: ""

        MoneyRequest(
            request = requestType,
            username = username,
            note = note,
            onBackPressed = { onBackPressed() }
        )
    }
}

fun NavController.navigateToMoneyRequest(requestType: Request, username: String, note: String) {
//    val encodedNote = encodeParam(note.ifEmpty { "no note mentioned" })
    val encodedNote = note.ifEmpty { "no note mentioned" }
    this.navigate("moneyRequestRoute/${requestType.name}/$username/$encodedNote")
}