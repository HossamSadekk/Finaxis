package presentation.splashScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import core.designSystem.theme.FinaxisTheme
import core.sharedPlatform.PlatformColors
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.finaxis
import finaxis.composeapp.generated.resources.poppins_bold
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.splashScreen.nav.SplashScreenDest
import presentation.splashScreen.nav.SplashScreenDest.KYC_INTRO
import presentation.splashScreen.nav.SplashScreenDest.WELCOME_SCREEN

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SplashScreen(onNavigation: (SplashScreenDest) -> Unit) {
    val viewModel = koinViewModel<SplashViewModel>()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    val scaleAnimationDuration = 1000 // Duration of the scale up and down animation
    val initialDelay = 1000 // Initial delay before starting the animation
    val delayBeforeEnd = 2000 // Duration to keep the final state before calling onSplashEnd

    var startAnimation by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1.5f else 1f,
        animationSpec = tween(durationMillis = scaleAnimationDuration, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        delay(initialDelay.toLong())
        startAnimation = true
        // Delay before scaling down
        delay(scaleAnimationDuration.toLong())
        startAnimation = false // Start scaling down
        // Delay for the final size before calling onSplashEnd
        delay(delayBeforeEnd.toLong())
        if (isLoggedIn) {
            onNavigation(KYC_INTRO)
        } else {
            onNavigation(WELCOME_SCREEN)
        }
    }

    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.onBackground,
        navBarColor = MaterialTheme.colorScheme.onBackground
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.finaxis),
            color = Color.White,
            fontSize = 30.sp * scale, // Apply animated scale
            style = TextStyle(
                fontFamily = FontFamily(Font(Res.font.poppins_bold)),
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    FinaxisTheme {
        SplashScreen { }
    }
}

