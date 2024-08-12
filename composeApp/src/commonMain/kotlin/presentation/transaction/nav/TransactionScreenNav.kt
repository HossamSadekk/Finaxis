package presentation.transaction.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.transaction.TransactionsScreen

const val transactionScreenRoute = "transactionScreenRoute"
fun NavGraphBuilder.transactionScreen() {
    composable(
        route = transactionScreenRoute,
    ) {
        TransactionsScreen()
    }
}
