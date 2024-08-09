package presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import core.sharedPlatform.PlatformColors
import dev.materii.pullrefresh.DragRefreshIndicator
import dev.materii.pullrefresh.DragRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_medium
import finaxis.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.home.components.BankCardUi
import presentation.home.components.HomeShimmerLoading
import presentation.home.components.TransactionItem

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )

    val viewModel = koinViewModel<HomeScreenViewModel>()
    val accountDetailsState by viewModel.accountDetails.collectAsState()

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.getAccountDetails()
            isRefreshing = false
        }
    )


    accountDetailsState.DisplayResult(
        onIdle = {
        },
        onLoading = {
            HomeShimmerLoading()
        },
        onSuccess = { accountDetails ->
            DragRefreshLayout(
                state = pullRefreshState,
                modifier = Modifier.fillMaxSize(),
                indicator = {
                    DragRefreshIndicator(
                        state = pullRefreshState,
                        flipped = false,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp, bottom = 50.dp)
                ) {
                    item {
                        BankCardUi(
                            userName = accountDetails.username ?: "Unknown",
                            totalBalance = accountDetails.balance?.let { "$it" } ?: "0",
                            cardNumber = accountDetails.cardNumber ?: "XXXX XXXX XXXX XXXX"
                        )
                        SendAndRequestMoney()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Transactions",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(resource = font.poppins_semibold))),
                            color = MaterialTheme.colorScheme.onSecondary,
                        )
                    }

                    items(accountDetails.transactions) { transaction ->
                        TransactionItem(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                            transaction = transaction,
                            accountId = accountDetails.id
                        )
                    }
                }
            }
        },
        onError = { errorMessage ->
            // Display error message
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }
    )
}

@Composable
fun SendAndRequestMoney() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 10.dp,
                bottom = 20.dp
            )
    ) {
        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .weight(1f)
                .height(60.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary)
        ) {
            Text(
                text = "Request Money",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(resource = font.poppins_medium))),
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }

        Button(
            onClick = {

            },
            modifier = Modifier
                .weight(1f)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Send Money",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(resource = font.poppins_medium))),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}