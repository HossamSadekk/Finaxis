package presentation.transaction.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.designSystem.components.shimmerEffect
import core.designSystem.theme.DpDimensions
import core.sharedPlatform.formatDate
import data.model.Transaction
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_regular
import org.jetbrains.compose.resources.Font

@Composable
fun TransactionItem(transaction: Transaction, accountId: Int) {
    val isSender = transaction.senderAccount.id == accountId

    val transactionState = when {
        isSender && transaction.type == "REQUEST" -> "You requested"
        isSender -> "You sent transaction"
        transaction.type == "TRANSFER" -> "You received transaction"
        else -> "You accepted request"
    }

    val senderDetails = if (isSender && transaction.type != "REQUEST") "To" else "From"

    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseSurface),
        shape = RoundedCornerShape(DpDimensions.Small),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DpDimensions.Small)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DpDimensions.Normal)
        ) {
            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            TransactionRow(
                label = transactionState,
                value = "${transaction.amount} EGP"
            )

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            TransactionRow(
                label = senderDetails,
                value = if (isSender) transaction.receiverAccount.username else transaction.senderAccount.username
            )

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            TransactionRow(
                label = "Transaction ID",
                value = transaction.id.toString()
            )

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            TransactionRow(
                label = "Date",
                value = formatDate(transaction.updatedAt)
            )

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            TransactionRow(
                label = "Note",
                value = transaction.note.orEmpty()
            )

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            TransactionRow(
                label = "Status",
                value = transaction.status
            )
        }
    }
}

@Composable
fun TransactionRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
            color = MaterialTheme.colorScheme.inversePrimary,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ShimmerTransactionItem() {
    Surface(
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.inverseSurface,
        ),
        shape = RoundedCornerShape(DpDimensions.Small),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DpDimensions.Small)
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
                ShimmerText(modifier = Modifier.weight(1f))
                ShimmerText(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerText(modifier = Modifier.weight(1f))
                ShimmerText(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerText(modifier = Modifier.weight(1f))
                ShimmerText(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerText(modifier = Modifier.weight(1f))
                ShimmerText(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(DpDimensions.Normal))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerText(modifier = Modifier.weight(1f))
                ShimmerText(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ShimmerText(modifier: Modifier) {
    Surface(
        modifier = modifier
            .height(20.dp)
            .padding(vertical = 4.dp)
            .shimmerEffect(),
        color = MaterialTheme.colorScheme.onTertiary
    ) {}
}
