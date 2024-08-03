package presentation.Bank.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import core.designSystem.components.shimmerEffect
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_medium
import org.jetbrains.compose.resources.Font

@Composable
fun BanksShimmerItem() {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
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
            ).shimmerEffect()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(50.dp)
                    .width(50.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
                    .weight(1f)
                    .shimmerEffect()
            )
        }
    }
}