package presentation.passcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designSystem.components.AppBar
import core.designSystem.components.CustomErrorDialog
import core.designSystem.components.LoaderWithOffset
import core.designSystem.components.NumberPad
import core.designSystem.components.PasscodeView
import core.network.utils.RequestState
import core.sharedPlatform.PlatformColors
import core.sharedPlatform.showToast
import core.utils.PASSCODE_LENGTH
import data.model.LoginRequest
import data.model.RegisterRequest
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_medium
import org.jetbrains.compose.resources.Font
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.passcode.nav.PasscodeScreenDest
import presentation.passcode.nav.PasscodeScreenDest.HomeScreen
import presentation.passcode.nav.PasscodeScreenDest.KYC

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PasscodeScreen(
    phoneNumber: String?,
    username: String?,
    onBackPressed: () -> Unit,
    onPasscodeSuccess: (PasscodeScreenDest) -> Unit,
) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    val viewModel = koinViewModel<PasscodeViewmodel>()
    var passcode by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val state by viewModel.tokenState.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val isUserHasAccount by viewModel.isUserHasAccount.collectAsState()
    val userPasscode by viewModel.passcode.collectAsState()

    LoaderWithOffset(
        isLoading = isLoading,
        offset = 50
    ) {
        Scaffold(
            topBar = {
                AppBar(modifier = Modifier.fillMaxWidth()) { onBackPressed() }
            }
        ) { paddingValues ->
            PasscodeContent(modifier = Modifier.padding(paddingValues), passcode,
                onNumberClick = {
                    if (passcode.length < PASSCODE_LENGTH) {
                        passcode += it
                        if (passcode.length == PASSCODE_LENGTH) {
                            when {
                                isLoggedIn -> handleLoggedInUser(
                                    passcode,
                                    userPasscode,
                                    isUserHasAccount
                                ) { destination ->
                                    onPasscodeSuccess(destination)
                                }

                                else -> handleUserRegistrationOrLogin(username, phoneNumber, passcode, viewModel)
                            }
                        }

                    }
                }, onDeleteAction = {
                    if (passcode.isNotEmpty()) {
                        passcode = passcode.dropLast(1)
                    }
                })
            when (state) {
                is RequestState.Idle -> {
                    Unit
                }

                is RequestState.Loading -> {
                    isLoading = true
                }

                is RequestState.Success -> {
                    isLoading = false
                    val response = (state as RequestState.Success).data
                    viewModel.apply {
                        cacheUserLoginState(token = response.token)
                        saveUserPasscode(passcode)
                    }
                    if(username != null && phoneNumber != null){
                        onPasscodeSuccess(KYC)
                    }else{
                        viewModel.setUserHasAccount()
                        onPasscodeSuccess(HomeScreen)
                    }
                }

                is RequestState.Error -> {
                    val error = (state as RequestState.Error).message
                    errorMessage = error
                    isLoading = false
                    showDialog = true
                }
            }
            if (showDialog) {
                CustomErrorDialog(
                    title = "Error",
                    message = errorMessage,
                    onDismiss = {
                        showDialog = false
                        viewModel.resetErrorState()
                    }
                )
            }
        }
    }
}

@Composable
fun PasscodeContent(modifier: Modifier, passcode: String, onNumberClick: (String) -> Unit, onDeleteAction: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter Passcode",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily(Font(resource = font.poppins_medium))
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(70.dp))

            PasscodeView(passcode = passcode)
        }

        NumberPad(
            onNumberClick = {
                onNumberClick(it)
            },
            onDeleteAction = {
                onDeleteAction()
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun handleLoggedInUser(
    passcode: String,
    userPasscode: String,
    hasAccount: Boolean,
    onPasscodeSuccess: (PasscodeScreenDest) -> Unit,
) {
    if (passcode == userPasscode) {
        val destination = if (hasAccount) HomeScreen else KYC
        onPasscodeSuccess(destination)
    } else {
        showToast("Passcode Is Wrong")
    }
}

// Function to handle registration or login based on provided details
private fun handleUserRegistrationOrLogin(
    username: String?,
    phoneNumber: String?,
    passcode: String,
    viewmodel: PasscodeViewmodel,
) {
    when {
        !username.isNullOrEmpty() && !phoneNumber.isNullOrEmpty() -> {
            // Perform registration
            viewmodel.registerUser(
                registerRequest = RegisterRequest(username, phoneNumber, passcode)
            )
        }

        else -> {
            // Perform login
            viewmodel.loginUser(
                loginRequest = LoginRequest(phoneNumber!!, passcode)
            )
        }
    }
}