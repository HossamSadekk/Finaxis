package core.designSystem.components

import KottieAnimation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_regular
import kotlinx.coroutines.delay
import kottieComposition.KottieCompositionSpec.File
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun SuccessBottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = MaterialTheme.colorScheme.onBackground
    ) {
        var animation by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            animation = Res.readBytes("files/success_lottie.json").decodeToString()
        }

        val composition = rememberKottieComposition(
            spec = File(animation)
        )

        val animationState by animateKottieCompositionAsState(
            composition = composition,
            iterations = 1,
            speed = 0.7f
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground).padding(vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KottieAnimation(
                composition = composition,
                progress = { animationState.progress },
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Congratulations! your accout created",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Spacer(modifier = Modifier.height(50.dp))

            LaunchedEffect(animationState.isCompleted) {
                if (animationState.isCompleted) {
                    delay(500L)
                    onDismiss()
                }
            }
        }
    }
}