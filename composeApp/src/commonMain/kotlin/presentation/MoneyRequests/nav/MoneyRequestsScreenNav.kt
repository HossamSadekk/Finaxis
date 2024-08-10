package presentation.MoneyRequests.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.MoneyRequests.MoneyRequestsScreen

const val moneyRequestsScreenRoute = "moneyRequestsScreenRoute"
fun NavGraphBuilder.RequestsScreen() {
    composable(
        route = moneyRequestsScreenRoute,
    ) {
        MoneyRequestsScreen()
    }
}
