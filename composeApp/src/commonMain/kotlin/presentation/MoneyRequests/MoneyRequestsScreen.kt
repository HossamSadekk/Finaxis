package presentation.MoneyRequests

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designSystem.components.CustomErrorDialog
import core.network.utils.RequestState.Error
import core.network.utils.RequestState.Success
import core.sharedPlatform.PlatformColors
import data.model.TransactionResponseModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.MoneyRequests.components.ShimmerTransactionItem
import presentation.MoneyRequests.components.TransactionItem

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MoneyRequestsScreen() {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    val viewModel = koinViewModel<RequestsScreenViewModel>()
    val requestsState by viewModel.requestsState.collectAsState()
    val respondState by viewModel.respondState.collectAsState()
    var errorMessage by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }
    var transactions by remember { mutableStateOf<List<TransactionResponseModel>>(emptyList()) }
    var loadingAction by remember { mutableStateOf<String?>(null) }
    var loadingTransactionId by remember { mutableStateOf<Int?>(null) }

    requestsState.DisplayResult(
        onIdle = {},
        onLoading = {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {
                items(10) {
                    ShimmerTransactionItem()
                }
            }
        },
        onSuccess = { transactionsList ->
            transactions = transactionsList
        },
        onError = {
            errorMessage = it
            showErrorDialog = true
        })
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
    // Display the list of transactions
    TransactionList(
        transactions = transactions,
        onAccept = { transactionId ->
            loadingTransactionId = transactionId
            loadingAction = "accept"
            viewModel.respondToRequest(transactionId.toString(), accept = true)
        },
        onRefuse = { transactionId ->
            loadingTransactionId = transactionId
            loadingAction = "refuse"
            viewModel.respondToRequest(transactionId.toString(), accept = false)
        },
        loadingTransactionId = loadingTransactionId,
        loadingAction = loadingAction
    )

    when (respondState) {
        is Error -> {
            loadingTransactionId = null
            loadingAction = null
        }

        is Success -> {
            loadingTransactionId = null
            loadingAction = null
        }

        else -> {}
    }
}

@Composable
fun TransactionList(
    transactions: List<TransactionResponseModel>,
    onAccept: (Int) -> Unit,
    onRefuse: (Int) -> Unit,
    loadingTransactionId: Int?,
    loadingAction: String?,
) {
    LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {
        items(transactions) { transaction ->
            TransactionItem(
                transaction = transaction,
                onAccept = onAccept,
                onRefuse = onRefuse,
                isAcceptLoading = loadingTransactionId == transaction.transactionId && loadingAction == "accept",
                isRefuseLoading = loadingTransactionId == transaction.transactionId && loadingAction == "refuse"
            )
        }
    }
}
