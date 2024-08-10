package presentation.money_request

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import core.designSystem.components.AppBar
import core.designSystem.components.CustomErrorDialog
import core.designSystem.components.FinaxisButton
import core.designSystem.components.Loader
import core.extensions.formatCardNumber
import core.network.utils.RequestState.Error
import core.network.utils.RequestState.Loading
import core.network.utils.RequestState.Success
import finaxis.composeapp.generated.resources.Res.drawable
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.avatar
import finaxis.composeapp.generated.resources.poppins_medium
import finaxis.composeapp.generated.resources.poppins_regular
import finaxis.composeapp.generated.resources.poppins_semibold
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.home.nav.Request
import presentation.home.nav.Request.REQUEST_MONEY
import presentation.home.nav.Request.SEND_MONEY
import presentation.money_request.components.ConfirmationDialog
import presentation.money_request.components.TransferMoneySuccessDialog

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MoneyRequest(request: Request, username: String, note: String, onBackPressed: () -> Unit) {
    val amountToSend = remember { mutableStateOf("0.0") }
    var walletVisible by remember { mutableStateOf(false) }
    var accountVisible by remember { mutableStateOf(false) }
    var balanceVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var isErrorHandled by remember { mutableStateOf(false) }
    val viewModel = koinViewModel<MoneyRequestViewModel>()
    val requestDetails by viewModel.requestDetailsState.collectAsState()
    val transferMoneyState by viewModel.transferMoneyState.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.getRequestDetails(username)
    }
    requestDetails.DisplayResult(
        onIdle = {},
        onLoading = {
            Loader()
        },
        onSuccess = {
            LaunchedEffect(Unit) {
                delay(500)
                walletVisible = true
                delay(500)
                accountVisible = true
                delay(500)
                balanceVisible = true
            }

            Scaffold(
                topBar = { AppBar(modifier = Modifier.fillMaxWidth()) { onBackPressed() } }
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues).fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 14.dp, end = 14.dp, top = 10.dp)
                            .weight(1f)
                    ) {
                        AnimatedVisibility(
                            visible = walletVisible,
                            enter = fadeIn() + slideInVertically(initialOffsetY = { -it }),
                            exit = fadeOut() + slideOutVertically(targetOffsetY = { -it })
                        ) {
                            WalletBalanceSection("${it.senderBalance} EGP")
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        AnimatedVisibility(
                            visible = accountVisible,
                            enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                            exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                        ) {
                            AccountSection(
                                name = it.receiverName,
                                accountNumber = it.receiverCardNumber.formatCardNumber()
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        AnimatedVisibility(
                            visible = balanceVisible,
                            enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                            exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                        ) {
                            BalanceInputField(
                                balance = amountToSend,
                                onBalanceChange = { newValue ->
                                    amountToSend.value = newValue
                                }
                            )
                        }
                    }
                    FinaxisButton(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        onClick = {
                            if (amountToSend.value.toDouble() > requestDetails.getSuccessData().senderBalance.toDouble() || amountToSend.value.toDouble() == 0.0) {
                                errorMessage = "Insufficient amount"
                                showErrorDialog = true
                                isErrorHandled = true
                            } else {
                                showConfirmationDialog = true
                            }
                        },
                        buttonText = "Finish",
                        isClickable = true,
                        isLoading = false
                    )
                }
            }
        },
        onError = {
            if (!isErrorHandled) {
                errorMessage = it
                showErrorDialog = true
                isErrorHandled = true
            }
        })
    if (showErrorDialog) {
        CustomErrorDialog(
            title = "Error",
            message = errorMessage,
            onDismiss = {
                showErrorDialog = false
                viewModel.resetTransferState()
            }
        )
    }

    if (showConfirmationDialog) {
        ConfirmationDialog(
            title = request.title,
            message = request.message,
            confirmButtonText = request.confirmButtonText,
            onConfirm = {
                showConfirmationDialog = false
                when (request) {
                    SEND_MONEY -> {
                        viewModel.transferMoney(
                            requestDetails.getSuccessData().senderUsername,
                            requestDetails.getSuccessData().receiverName, amountToSend.value.toDouble(), note
                        )
                    }

                    REQUEST_MONEY -> {
                        // TODO :: Handle Request
                    }
                }
            },
            onDismiss = {
                showConfirmationDialog = false
            }
        )
    }

    when (transferMoneyState) {
        is Error -> {
            errorMessage = transferMoneyState.getErrorMessage()
            showErrorDialog = true
        }

        Loading -> {
            Loader()
        }

        else -> {}
    }
    LaunchedEffect(transferMoneyState) {
        if (transferMoneyState is Success) {
            showSuccessDialog = true
        }
    }

    if (showSuccessDialog) {
        TransferMoneySuccessDialog(transferMoneyState.getSuccessData()) {
            showSuccessDialog = false
        }
    }
}

@Composable
fun WalletBalanceSection(balance: String) {
    Surface(
        color = Color(0xFF1364FF),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Wallet balance",
                color = Color(0xFFB0C4FF),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = FontFamily(
                        Font(font.poppins_regular)
                    )
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = balance,
                color = Color.White,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontFamily = FontFamily(
                        Font(font.poppins_semibold)
                    )
                )
            )
        }
    }
}

@Composable
fun AccountSection(name: String, accountNumber: String) {
    Surface(
        color = Color(0xFF262626),
        shape = RoundedCornerShape(40.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(resource = drawable.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = accountNumber,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceInputField(
    balance: MutableState<String>,
    onBalanceChange: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(
                    value = balance.value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.toFloatOrNull() != null) {
                            onBalanceChange(newValue)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedTextColor = MaterialTheme.colorScheme.onBackground
                    ),
                    textStyle = MaterialTheme.typography.displayMedium.copy(
                        fontFamily = FontFamily(
                            Font(font.poppins_semibold)
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 32.dp)
                        .background(Color.Transparent),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = "EGP",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(
                            Font(font.poppins_medium)
                        )
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 8.dp)
                )
            }
        }
    }
}

