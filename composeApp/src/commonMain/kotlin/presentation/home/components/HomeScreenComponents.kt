package presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.extensions.formatCardNumber
import core.sharedPlatform.formatDate
import data.model.Transaction
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.avatar
import finaxis.composeapp.generated.resources.ic_request_money
import finaxis.composeapp.generated.resources.ic_send_request
import finaxis.composeapp.generated.resources.poppins_light
import finaxis.composeapp.generated.resources.poppins_medium
import finaxis.composeapp.generated.resources.poppins_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BankCardUi(
    userName: String,
    totalBalance: String,
    cardNumber: String,
) {
    val bankCardAspectRatio = 1.586f // (e.g., width:height = 85.60mm:53.98mm)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,
        shape = RoundedCornerShape(35.dp),
        elevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    // Avatar
                    Image(
                        painter = painterResource(resource = Res.drawable.avatar),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Column with texts
                    Column {
                        Text(
                            text = "Hello ⛅",
                            style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(resource = font.poppins_light))),
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            text = userName,
                            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(resource = font.poppins_medium))),
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(23.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Total Balance",
                        style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(resource = font.poppins_light))),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "$totalBalance EGP",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 25.sp,
                            fontFamily = FontFamily(Font(resource = font.poppins_medium))
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
            Text(
                text = cardNumber.formatCardNumber(),
                style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily(Font(resource = font.poppins_regular))),
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionItem(modifier: Modifier = Modifier, transaction: Transaction, accountId: Int) {
    val isSender = transaction.senderAccount.id == accountId
    val amountText = if (isSender) {
        "-${transaction.amount}"
    } else {
        "+${transaction.amount}"
    }
    val amountColor = if (isSender) Color.Red else Color.Green
    val transactionText = if (isSender) {
        "Send request to ${transaction.receiverAccount.username}"
    } else {
        if(transaction.type == "TRANSFER"){
            "Received from ${transaction.senderAccount.username}"
        }else{
            "Request from ${transaction.senderAccount.username}"
        }
    }
    val formattedDate = formatDate(transaction.createdAt)
    val icon =
        if (isSender) vectorResource(resource = Res.drawable.ic_send_request) else vectorResource(resource = Res.drawable.ic_request_money)

    Surface(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.inverseSurface, CircleShape)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Business icon",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.inversePrimary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transactionText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily(Font(Res.font.poppins_medium))),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(Res.font.poppins_regular))),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = amountText,
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(Res.font.poppins_medium))),
                color = amountColor
            )
        }
    }
}