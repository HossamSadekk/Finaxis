package presentation.MoneyRequests.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import core.designSystem.components.FinaxisButton
import core.designSystem.components.shimmerEffect
import core.designSystem.theme.DpDimensions
import data.model.TransactionResponseModel
import finaxis.composeapp.generated.resources.Res.drawable
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.avatar
import finaxis.composeapp.generated.resources.poppins_regular
import finaxis.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransactionItem(
    transaction: TransactionResponseModel,
    onAccept: (Int) -> Unit,
    onRefuse: (Int) -> Unit,
    isAcceptLoading: Boolean,
    isRefuseLoading: Boolean,
) {
    Surface(
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.inverseSurface,
        ),
        shape = RoundedCornerShape(DpDimensions.Small),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier.padding(top = 16.dp, bottom = 5.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(18.dp)) {
            Image(
                painter = painterResource(resource = drawable.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${transaction.senderUsername} request ${transaction.amount} EGP from you",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_semibold))),
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = transaction.note,
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                color = MaterialTheme.colorScheme.onTertiary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                FinaxisButton(
                    modifier = Modifier.weight(1f).height(40.dp), buttonText = "Accept", isClickable = !isAcceptLoading,
                    isLoading = isAcceptLoading, onClick = {
                        onAccept(transaction.transactionId)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                FinaxisButton(
                    modifier = Modifier.weight(1f).height(40.dp), buttonText = "Refuse", isClickable = !isRefuseLoading,
                    isLoading = isRefuseLoading, onClick = {
                        onRefuse(transaction.transactionId)
                    }
                )
            }
        }
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
            .padding(top = 16.dp, bottom = 5.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(18.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Gray)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(Color.Gray)
                )
            }
        }

    }
}
