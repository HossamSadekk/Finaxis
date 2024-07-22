package presentation.welcomeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designSystem.components.FinaxisWideButton
import core.sharedPlatform.PlatformColors
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.discover_the_power_of_seamless
import finaxis.composeapp.generated.resources.finaxis
import finaxis.composeapp.generated.resources.poppins_bold
import finaxis.composeapp.generated.resources.poppins_light
import finaxis.composeapp.generated.resources.sign_in
import finaxis.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import presentation.welcomeScreen.nav.WelcomeScreenDest
import presentation.welcomeScreen.nav.WelcomeScreenDest.SIGN_IN
import presentation.welcomeScreen.nav.WelcomeScreenDest.SIGN_UP

@Composable
fun WelcomeScreen(onNavigation: (WelcomeScreenDest) -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WelcomeContent(modifier = Modifier.weight(1f).padding(32.dp))
            BottomButtons(modifier = Modifier.padding(horizontal = 32.dp),
                onSignInClick = {
                    onNavigation(SIGN_IN)
                }, onSignUpClick = {
                    onNavigation(SIGN_UP)
                }
            )
        }
    }
}

@Composable
fun WelcomeContent(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(resource = Res.string.finaxis),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(resource = Res.font.poppins_bold)))
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(resource = Res.string.discover_the_power_of_seamless),
            style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily(Font(resource = Res.font.poppins_light))),
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomButtons(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    // State to control visibility for animation
    var isVisible by remember { mutableStateOf(false) }

    // Trigger visibility change when the composable first enters the screen
    LaunchedEffect(Unit) {
        isVisible = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Divider(
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Sign In Button Animation
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(
                initialOffsetX = { -it }, // Slide in from left
                animationSpec = tween(durationMillis = 1500)
            )
        ) {
            FinaxisWideButton(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp), // Adjust padding if needed
                buttonText = stringResource(resource = Res.string.sign_in),
                onClick = { onSignInClick() }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Sign Up Button Animation
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(
                initialOffsetX = { it }, // Slide in from right
                animationSpec = tween(durationMillis = 1500)
            )
        ) {
            FinaxisWideButton(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp), // Adjust padding if needed
                buttonText = stringResource(resource = Res.string.sign_up),
                onClick = { onSignUpClick() }
            )
        }
    }
}