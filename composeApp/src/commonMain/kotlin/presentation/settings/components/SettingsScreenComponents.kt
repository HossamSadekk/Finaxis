package presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.designSystem.theme.DpDimensions
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SettingsItem(
    title: String,
    icon: DrawableResource,
    iconTint: Color,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(DpDimensions.Normal),
        onClick = { onClick() },
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
        ) {

            Box(
                modifier = Modifier
                    .padding(DpDimensions.Normal)
                    .background(iconTint.copy(alpha = .1f), CircleShape)
                    .clip(CircleShape)
                    .size(50.dp),
                contentAlignment = Alignment.Center,
            ) {

                Icon(
                    imageVector = vectorResource(resource = icon),
                    contentDescription = null,
                    modifier = Modifier.size(DpDimensions.Dp20),
                    tint = iconTint
                )

            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.weight(1f)
            )

        }
    }
}
data class SettingOption(
    val title: String,
    val icon: DrawableResource,
    val iconTint: Color
)