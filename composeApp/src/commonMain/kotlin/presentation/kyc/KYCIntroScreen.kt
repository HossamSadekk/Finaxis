package presentation.kyc

import KottieAnimation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import core.designSystem.components.FinaxisWideButton
import core.sharedPlatform.PlatformColors
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.Res.string
import finaxis.composeapp.generated.resources.kyc_intro_description
import finaxis.composeapp.generated.resources.kyc_intro_title
import finaxis.composeapp.generated.resources.poppins_bold
import finaxis.composeapp.generated.resources.poppins_regular
import kottieComposition.KottieCompositionSpec
import kottieComposition.animateKottieCompositionAsState
import kottieComposition.rememberKottieComposition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import utils.KottieConstants

@OptIn(ExperimentalResourceApi::class)
@Composable
fun KycIntroScreen(onProceedPressed: () -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )

    var animation by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        animation = Res.readBytes("files/kyc_animation.json").decodeToString()
    }

    val composition = rememberKottieComposition(
        spec = KottieCompositionSpec.File(animation)
    )

    val animationState by animateKottieCompositionAsState(
        composition = composition,
        iterations = KottieConstants.IterateForever
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(string.kyc_intro_title),
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(font.poppins_bold))),
                color = MaterialTheme.colorScheme.inversePrimary,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(string.kyc_intro_description),
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Spacer(modifier = Modifier.height(50.dp))
            KottieAnimation(
                composition = composition,
                progress = { animationState.progress },
                modifier = Modifier.size(300.dp)
            )
        }
        FinaxisWideButton(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            onClick = { onProceedPressed() },
            buttonText = "Proceed"
        )
    }
}
