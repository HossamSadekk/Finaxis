package presentation.authScreen.signin

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
import core.designSystem.helper.PhoneNumberState
import core.sharedPlatform.PlatformColors
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.Res.string
import finaxis.composeapp.generated.resources.poppins_bold
import finaxis.composeapp.generated.resources.poppins_regular
import finaxis.composeapp.generated.resources.signIn_description
import finaxis.composeapp.generated.resources.welcome_back
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignInScreen(onBackPressed: () -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.onBackground,
        navBarColor = MaterialTheme.colorScheme.onBackground
    )
    var phoneNumberState by remember { mutableStateOf(PhoneNumberState()) }
    var isLoading by remember { mutableStateOf(false) }
    var isClickable by remember { mutableStateOf(false) }

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
                    text = stringResource(resource = string.welcome_back),
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(font.poppins_bold))),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(string.signIn_description),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(40.dp))
                PhoneNumberSection(
                    phoneNumberState = phoneNumberState,
                    onPhoneNumberChange = { newPhoneNumber ->
                        phoneNumberState = phoneNumberState.copy(phoneNumber = newPhoneNumber)
                    }
                )
            }
            FinaxisButton(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                onClick = {
                    isLoading = true
                },
                buttonText = "Submit",
                isClickable = isClickable,
                isLoading = isLoading
            )
        }
    }
}