package presentation.authScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designSystem.components.AppBar
import core.designSystem.components.FinaxisButton
import core.designSystem.components.PhoneNumberSection
import core.designSystem.components.UsernameSection
import core.designSystem.helper.PhoneNumberState
import core.designSystem.helper.UsernameState
import core.extensions.isValidNumber
import core.extensions.isValidUsername
import core.sharedPlatform.PlatformColors
import core.sharedPlatform.showToast
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.please_enter_your_phone_number
import finaxis.composeapp.generated.resources.poppins_bold
import finaxis.composeapp.generated.resources.poppins_regular
import finaxis.composeapp.generated.resources.signup_description
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpScreen(onBackPressed: () -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.onBackground,
        navBarColor = MaterialTheme.colorScheme.onBackground
    )

    var phoneNumberState by remember { mutableStateOf(PhoneNumberState()) }
    var usernameState by remember { mutableStateOf(UsernameState()) }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppBar(onBackClicked = onBackPressed) }
    ) { paddingValues ->
        SignUpContent(
            paddingValues = paddingValues,
            phoneNumberState = phoneNumberState,
            onPhoneNumberChange = { newPhoneNumber ->
                phoneNumberState = phoneNumberState.copy(phoneNumber = newPhoneNumber)
            },
            usernameState = usernameState,
            onUsernameChange = { newUsername ->
                usernameState = usernameState.copy(username = newUsername)
            },
            isLoading = isLoading,
            isClickable = true,
            onSubmit = {
                when {
                    !usernameState.username.isValidUsername() -> {
                        // Handle invalid username
                        showToast("please provide a valid username")

                    }

                    !phoneNumberState.phoneNumber.isValidNumber() -> {
                        // Handle invalid phone number
                        showToast("please provide a valid phone number")

                    }

                    else -> {
                        // Call signup function
                    }
                }
            }
        )
    }
}

@Composable
fun SignUpContent(
    paddingValues: PaddingValues,
    phoneNumberState: PhoneNumberState,
    onPhoneNumberChange: (String) -> Unit,
    usernameState: UsernameState,
    onUsernameChange: (String) -> Unit,
    isLoading: Boolean,
    isClickable: Boolean,
    onSubmit: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = stringResource(Res.string.please_enter_your_phone_number),
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(Res.font.poppins_bold))),
                color = MaterialTheme.colorScheme.inversePrimary,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(Res.string.signup_description),
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(Res.font.poppins_regular))),
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Spacer(modifier = Modifier.height(40.dp))
            UsernameSection(usernameState = usernameState, onUsernameChange = onUsernameChange)
            Spacer(modifier = Modifier.height(15.dp))
            PhoneNumberSection(
                phoneNumberState = phoneNumberState,
                onPhoneNumberChange = onPhoneNumberChange
            )
        }
        FinaxisButton(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            onClick = onSubmit,
            buttonText = "Submit",
            isClickable = isClickable,
            isLoading = isLoading
        )
    }
}