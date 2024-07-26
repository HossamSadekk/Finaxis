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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import core.designSystem.components.NumberPad
import core.designSystem.components.PasscodeView
import core.sharedPlatform.PlatformColors
import core.utils.PASSCODE_LENGTH
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_medium
import org.jetbrains.compose.resources.Font

@Composable
fun PasscodeScreen() {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    var passcode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Adjusts spacing between elements
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp), // Padding from the top
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centers the content within this column
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
                modifier = Modifier.padding(bottom = 16.dp) // Padding between text and passcode view
            )
            Spacer(modifier = Modifier.height(70.dp))

            PasscodeView(passcode = passcode)
        }

        // NumberPad positioned at the bottom of the parent Column
        NumberPad(
            onNumberClick = {
                if (passcode.length < PASSCODE_LENGTH) {
                    passcode += it
                    if (passcode.length == PASSCODE_LENGTH) {
                        // TODO :: Call on complete function
                    }
                }
            },
            onDeleteAction = {
                if (passcode.isNotEmpty()) {
                    passcode = passcode.dropLast(1)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}