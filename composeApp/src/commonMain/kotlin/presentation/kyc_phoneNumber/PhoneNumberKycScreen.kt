package presentation.kyc_phoneNumber

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designSystem.components.AppBar
import core.designSystem.components.CustomErrorDialog
import core.designSystem.components.FinaxisButton
import core.designSystem.components.PhoneNumberSection
import core.designSystem.helper.PhoneNumberState
import core.extensions.isValidNumber
import core.network.utils.RequestState.Error
import core.network.utils.RequestState.Success
import core.sharedPlatform.PlatformColors
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.Res.string
import finaxis.composeapp.generated.resources.kyc_second_step_description
import finaxis.composeapp.generated.resources.kyc_second_step_title
import finaxis.composeapp.generated.resources.poppins_regular
import finaxis.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PhoneNumberKycScreen(onBackPressed: () -> Unit, onProceed: () -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.onBackground,
        navBarColor = MaterialTheme.colorScheme.onBackground
    )
    var phoneNumberState by remember { mutableStateOf(PhoneNumberState()) }
    val viewModel = koinViewModel<PhoneNumberKycViewModel>()
    val submitPhoneNumber by viewModel.phoneNumberState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppBar(modifier = Modifier.fillMaxWidth()) { onBackPressed() } }
    ) { paddingValues ->
        Column(
            modifier =
            Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 10.dp
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(resource = string.kyc_second_step_title),
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(font.poppins_semibold))),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(string.kyc_second_step_description),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(15.dp))
                PhoneNumberSection(
                    phoneNumberState = phoneNumberState,
                    onPhoneNumberChange = { newPhoneNumber ->
                        phoneNumberState = phoneNumberState.copy(phoneNumber = newPhoneNumber)
                    }
                )
            }
            FinaxisButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    viewModel.setPhoneNumber(phoneNumberState.phoneNumber)
                },
                buttonText = "Proceed",
                isClickable = phoneNumberState.phoneNumber.isValidNumber(),
                isLoading = submitPhoneNumber.isLoading()
            )
            if (submitPhoneNumber.isError()) {
                showDialog = true
            }

            when (submitPhoneNumber) {
                is Error -> {
                    showDialog = true
                }

                is Success -> {
                    onProceed()
                }

                else -> {}
            }

            if (showDialog) {
                CustomErrorDialog(
                    title = "Error",
                    message = submitPhoneNumber.getErrorMessage(),
                    onDismiss = {
                        showDialog = false
                        viewModel.resetErrorState()
                    }
                )
            }
        }
    }
}