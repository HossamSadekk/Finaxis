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
import core.designSystem.components.NumberPad
import core.designSystem.components.PasscodeView
import core.network.utils.RequestState
import core.sharedPlatform.PlatformColors
import core.utils.PASSCODE_LENGTH
import data.model.RegisterRequest
import data.model.TokenResponse
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_medium
import org.jetbrains.compose.resources.Font
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.passcode.PasscodeViewmodel.Companion.REGISTER_STATE

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PasscodeScreen(phoneNumber: String?, username: String?, onBackPressed: () -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    val viewModel = koinViewModel<PasscodeViewmodel>()
    var passcode by remember { mutableStateOf("") }
    val state by viewModel.tokenState.collectAsState()

    Scaffold(
        topBar = { AppBar(modifier = Modifier.fillMaxWidth()) { onBackPressed() } }
    ) { paddingValues ->
        PasscodeContent(modifier = Modifier.padding(paddingValues), passcode,
            onNumberClick = {
                if (passcode.length < PASSCODE_LENGTH) {
                    passcode += it
                    if (passcode.length == PASSCODE_LENGTH) {
                        viewModel.registerUser(
                            registerRequest = RegisterRequest(
                                username.orEmpty(),
                                phoneNumber.orEmpty(),
                                passcode
                            )
                        )
                    }
                }
            }, onDeleteAction = {
                if (passcode.isNotEmpty()) {
                    passcode = passcode.dropLast(1)
                }
            })
        when (state) {
            is RequestState.Idle -> {
                Text("idle")
            }
            is RequestState.Loading -> {
                println("loading")
            }
            is RequestState.Success -> {
                val user = (state as RequestState.Success).data
                println("Success ${user.token}")
            }
            is RequestState.Error -> {
                println("Error: ${(state as RequestState.Error).message}")
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