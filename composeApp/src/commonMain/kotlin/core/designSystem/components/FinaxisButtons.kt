package core.designSystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.poppins_regular
import org.jetbrains.compose.resources.Font

@Composable
fun FinaxisWideButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    height: Dp = 50.dp,
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .fillMaxWidth(1f)
            .height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.titleSmall.copy(fontFamily = FontFamily(Font(Res.font.poppins_regular))),
            color = textColor
        )
    }
}