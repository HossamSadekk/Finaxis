import androidx.compose.runtime.Composable
import core.designSystem.theme.FinaxisTheme
import core.navigation.MainNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    FinaxisTheme {
        MainNavigation()
    }
}