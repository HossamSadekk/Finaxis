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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import core.designSystem.helper.CardNumberState
import core.designSystem.helper.CardPasswordState
import core.designSystem.helper.PhoneNumberState
import core.designSystem.helper.UsernameState
import core.utils.CardNumberMask
import finaxis.composeapp.generated.resources.Res.drawable
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.Res.string
import finaxis.composeapp.generated.resources.cardNumber
import finaxis.composeapp.generated.resources.cardPassword
import finaxis.composeapp.generated.resources.ic_credit_card
import finaxis.composeapp.generated.resources.ic_password
import finaxis.composeapp.generated.resources.phone_number
import finaxis.composeapp.generated.resources.poppins_light
import finaxis.composeapp.generated.resources.username
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

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
                    if (sanitizedValue.length <= 11) {
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

/** Card Info  **/

@Composable
fun CardNumberSection(
    modifier: Modifier = Modifier,
    cardNumberState: CardNumberState,
    onCardNumberChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(string.cardNumber), // Update with the appropriate string resource ID
            color = MaterialTheme.colorScheme.inversePrimary,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        EnterCardNumber(
            state = cardNumberState,
            onCardNumberChange = onCardNumberChange
        )
    }
}

@Composable
fun EnterCardNumber(
    modifier: Modifier = Modifier,
    state: CardNumberState,
    onCardNumberChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf(TextFieldValue(state.cardNumber)) }
    // Apply the CardNumberMask for formatting

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
                imageVector = vectorResource(drawable.ic_credit_card), // Replace with an appropriate card number icon if you have one
                contentDescription = "Card number icon",
                modifier = Modifier.size(24.dp)
            )

            TextField(
                value = state.cardNumber,
                onValueChange = { newValue ->
                    onCardNumberChange(newValue)
                },
                placeholder = {
                    Text(
                        text = "1234-5678-9012-3456",
                        style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_light)))
                    )
                },
                visualTransformation = CardNumberMask("-", false),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/** card password **/

@Composable
fun CardPasswordSection(
    modifier: Modifier = Modifier,
    cardPasswordState: CardPasswordState,
    onCardPasswordChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(string.cardPassword), // Update with the appropriate string resource ID
            color = MaterialTheme.colorScheme.inversePrimary,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        EnterCardPassword(
            state = cardPasswordState,
            onCardPasswordChange = onCardPasswordChange
        )
    }
}

@Composable
fun EnterCardPassword(
    modifier: Modifier = Modifier,
    state: CardPasswordState,
    onCardPasswordChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf(TextFieldValue(state.cardPassword)) }

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
                imageVector = vectorResource(drawable.ic_password), // Replace with an appropriate password icon
                contentDescription = "Password icon",
                modifier = Modifier.size(24.dp)
            )

            TextField(
                value = text,
                onValueChange = { newValue ->
                    val input = newValue.text.take(4) // Ensure only 4 characters
                    text = newValue.copy(text = input)
                    onCardPasswordChange(input)
                },
                placeholder = {
                    Text(
                        text = "XXXX",
                        style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_light)))
                    )
                },
                visualTransformation = PasswordVisualTransformation(), // Mask the password input
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
