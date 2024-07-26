package core.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import core.designSystem.theme.DpDimensions
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_medium
import finaxis.composeapp.generated.resources.poppins_regular
import org.jetbrains.compose.resources.Font

@Composable
fun NumberPad(
    modifier: Modifier = Modifier,
    onNumberClick: (String) -> Unit,
    onDeleteAction: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(DpDimensions.Dp20))
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(
                onClick = { onNumberClick("1") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ),
                    color = MaterialTheme.colorScheme.inversePrimary,
                )
            }

            TextButton(
                onClick = { onNumberClick("2") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "2",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            TextButton(
                onClick = { onNumberClick("3") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(
                onClick = { onNumberClick("4") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "4",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            TextButton(
                onClick = { onNumberClick("5") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "5",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            TextButton(
                onClick = { onNumberClick("6") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "6",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(
                onClick = { onNumberClick("7") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "7",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            TextButton(
                onClick = { onNumberClick("8") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "8",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            TextButton(
                onClick = { onNumberClick("9") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "9",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(
                onClick = {

                },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = ".",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            TextButton(
                onClick = {
                    onNumberClick("0")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "0",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_medium))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            TextButton(
                onClick = {
                    onDeleteAction()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = "Delete",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily(Font(resource = font.poppins_regular))
                    ), color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(DpDimensions.Dp20))
    }

}