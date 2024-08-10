package presentation.money_request.components

import KottieAnimation
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import core.designSystem.theme.DpDimensions
import core.sharedPlatform.formatDate
import data.model.TransactionResponseModel
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_light
import finaxis.composeapp.generated.resources.poppins_medium
import finaxis.composeapp.generated.resources.poppins_regular
import finaxis.composeapp.generated.resources.poppins_semibold
import kottieComposition.KottieCompositionSpec.File
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    confirmButtonText: String,
    dismissButtonText: String = "Cancel",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily(
                        Font(font.poppins_semibold)
                    )
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = FontFamily(
                        Font(font.poppins_regular)
                    )
                )
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = confirmButtonText,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily(
                            Font(font.poppins_light)
                        )
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = dismissButtonText,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily(
                            Font(font.poppins_light)
                        )
                    )
                )
            }
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TransferMoneySuccessDialog(
    transactionResponseModel: TransactionResponseModel,
    onDismiss: () -> Unit,
) {
    var animation by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        animation = Res.readBytes("files/money_request.json").decodeToString()
    }

    val composition = rememberKottieComposition(
        spec = File(animation)
    )

    val animationState by animateKottieCompositionAsState(
        composition = composition,
        iterations = 1,
        speed = 1f
    )

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                KottieAnimation(
                    composition = composition,
                    progress = { animationState.progress },
                    modifier = Modifier.size(150.dp),
                    backgroundColor = MaterialTheme.colorScheme.surface
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${transactionResponseModel.amount} EGP",
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily(Font(font.poppins_semibold))),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Sent to ${transactionResponseModel.receiverUsername}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_medium))),
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Spacer(modifier = Modifier.height(DpDimensions.Normal))
                Surface(
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    ),
                    shape = RoundedCornerShape(DpDimensions.Small),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(DpDimensions.Normal)
                    ) {

                        Spacer(modifier = Modifier.height(DpDimensions.Normal))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Transaction ID",
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = transactionResponseModel.transactionId.toString(),
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.inversePrimary,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.End
                            )
                        }
                        Spacer(modifier = Modifier.height(DpDimensions.Normal))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Date",
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = formatDate(transactionResponseModel.updatedAt),
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.inversePrimary,
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(DpDimensions.Normal))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Note",
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = transactionResponseModel.note,
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.inversePrimary,
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(DpDimensions.Normal))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Status",
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = transactionResponseModel.status,
                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                                color = MaterialTheme.colorScheme.inversePrimary,
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}
