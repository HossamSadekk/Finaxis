package core.designSystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.utils.PASSCODE_LENGTH

@Composable
fun PasscodeView(passcode: String, passcodeLength: Int = PASSCODE_LENGTH) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        for (i in 0 until passcodeLength) {
            PasscodeDot(filled = i < passcode.length)
        }
    }
}

@Composable
fun PasscodeDot(filled: Boolean) {
    val animatedColor by animateColorAsState(
        targetValue = if (filled) MaterialTheme.colorScheme.onPrimary else Color.Gray,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 9.dp)
            .size(17.dp)
            .background(
                color = animatedColor,
                shape = CircleShape
            )
    )
}