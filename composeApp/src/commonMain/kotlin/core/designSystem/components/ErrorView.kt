package core.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_light
import finaxis.composeapp.generated.resources.poppins_medium

@Composable
fun CustomErrorDialog(title: String, message: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(
                            org.jetbrains.compose.resources.Font(
                                font.poppins_medium
                            )
                        )
                    ),
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily(
                            org.jetbrains.compose.resources.Font(
                                font.poppins_light
                            )
                        )
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text(text = "Ok")
                }
            }
        }
    }
}