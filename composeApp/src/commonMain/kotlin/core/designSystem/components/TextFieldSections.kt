package core.designSystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import core.designSystem.helper.PhoneNumberState
import core.designSystem.helper.UsernameState
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.Res.string
import finaxis.composeapp.generated.resources.phone_number
import finaxis.composeapp.generated.resources.poppins_light
import finaxis.composeapp.generated.resources.username
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

/** Phone number Section **/

@Composable
fun PhoneNumberSection(
    modifier: Modifier = Modifier,
    phoneNumberState: PhoneNumberState,
    onPhoneNumberChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(resource = string.phone_number),
            color = MaterialTheme.colorScheme.inversePrimary,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        EnterPhoneNumber(
            state = phoneNumberState,
            onPhoneNumberChange = onPhoneNumberChange
        )
    }
}

@Composable
fun EnterPhoneNumber(
    modifier: Modifier = Modifier,
    state: PhoneNumberState,
    onPhoneNumberChange: (String) -> Unit,
) {
    val validPrefixes = listOf("010", "011", "012", "015")

    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone icon",
                modifier = Modifier.size(24.dp)
            )

            TextField(
                value = state.phoneNumber,
                onValueChange = { value ->
                    val sanitizedValue = value.filter { it.isDigit() }
                    if (sanitizedValue.length <= 10) {
                        val prefixLength = validPrefixes.minOfOrNull { it.length } ?: 0
                        val startsWithValidPrefix = sanitizedValue.length >= prefixLength &&
                                                    validPrefixes.any { sanitizedValue.startsWith(it) }

                        if (startsWithValidPrefix || sanitizedValue.length < prefixLength) {
                            onPhoneNumberChange(sanitizedValue)
                        }
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(string.phone_number), // Update with the appropriate string resource ID
                        style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_light)))
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                textStyle = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/** Username Section **/

@Composable
fun UsernameSection(
    modifier: Modifier = Modifier,
    usernameState: UsernameState,
    onUsernameChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(resource = string.username),
            color = MaterialTheme.colorScheme.inversePrimary,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        EnterUsername(
            state = usernameState,
            onUsernameChange = onUsernameChange
        )
    }
}

@Composable
fun EnterUsername(
    modifier: Modifier = Modifier,
    state: UsernameState,
    onUsernameChange: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person, // Replace with an appropriate username icon if you have one
                contentDescription = "Username icon",
                modifier = Modifier.size(24.dp)
            )

            TextField(
                value = state.username,
                onValueChange = onUsernameChange,
                placeholder = {
                    Text(
                        text = stringResource(resource = string.username), // Update with the appropriate string resource ID
                        style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_light)))
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
