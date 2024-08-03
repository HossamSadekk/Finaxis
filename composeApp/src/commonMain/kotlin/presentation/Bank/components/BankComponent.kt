package presentation.Bank.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberAsyncImagePainter
import data.model.Bank
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_medium
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BankComponent(modifier: Modifier = Modifier, bank: Bank, onBankSelected: (Bank) -> Unit) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth(),
        border = BorderStroke(
            1.dp,
            if (bank.isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.inverseSurface
        ),
        onClick = { onBankSelected(bank) },
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
            Image(
                painter = rememberAsyncImagePainter(bank.avatar),
                contentDescription = bank.name,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(50.dp)
                    .width(50.dp),
                contentScale = ContentScale.Inside
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = bank.name,
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily(Font(font.poppins_medium))),
                color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}