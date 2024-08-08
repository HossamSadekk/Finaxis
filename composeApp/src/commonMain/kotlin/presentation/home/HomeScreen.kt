package presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.sharedPlatform.PlatformColors
import presentation.home.components.BankCardUi

@Composable
fun HomeScreen() {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.background,
        navBarColor = MaterialTheme.colorScheme.background
    )
    Column(
        modifier = Modifier
            .fillMaxSize().padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BankCardUi(userName = "Hossam933", totalBalance = "34,000", cardNumber = "3421123454545233")
    }
}