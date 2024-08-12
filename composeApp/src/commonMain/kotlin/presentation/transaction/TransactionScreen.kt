package presentation.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designSystem.components.CustomErrorDialog
import core.designSystem.theme.DpDimensions
import core.sharedPlatform.PlatformColors
import data.model.Transaction
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.transaction.components.ShimmerTransactionItem
import presentation.transaction.components.TransactionItem

@OptIn(KoinExperimentalAPI::class)
@Composable
fun TransactionsScreen() {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    val viewModel = koinViewModel<TransactionViewModel>()
    val transactionState by viewModel.transactionState.collectAsState()
    val accountState by viewModel.accountState.collectAsState()
    var errorMessage by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.apply {
            getAccountId()
            getTransactionList()
        }
    }
    transactionState.DisplayResult(
        onIdle = {},
        onLoading = {
            LazyColumn(
                contentPadding = PaddingValues(DpDimensions.Small),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(10) {
                    ShimmerTransactionItem()
                }
            }
        },
        onSuccess = {
            TransactionList(
                transactions = it,
                accountId = accountState
            )
        },
        onError = {
            errorMessage = it
            showErrorDialog = true
        }
    )
    /** Error Dialog **/
    if (showErrorDialog) {
        CustomErrorDialog(
            title = "Error",
            message = errorMessage,
            onDismiss = {
                showErrorDialog = false
                viewModel.resetState()
            }
        )
    }

}

@Composable
fun TransactionList(
    transactions: List<Transaction>,
    accountId: Int,
) {
    LazyColumn(
        contentPadding = PaddingValues(DpDimensions.Small),
        verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.padding(bottom = 70.dp)
    ) {
        items(transactions) { transaction ->
            TransactionItem(transaction = transaction, accountId)
        }
    }
}
