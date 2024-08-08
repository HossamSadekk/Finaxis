package core.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import presentation.home.nav.homeScreenRoute
import presentation.MoneyRequests.nav.moneyRequestsScreenRoute
import presentation.settings.nav.settingsScreenRoute

// Define the tabs
sealed class BottomBarTab(val title: String, val icon: ImageVector, val color: Color, val route: String) {
    data object Profile : BottomBarTab(
        title = "Transaction",
        icon = Icons.Rounded.Person,
        color = Color(0xFFFFA574),
        route = ""
    )

    data object Home : BottomBarTab(
        title = "Home",
        icon = Icons.Rounded.Home,
        color = Color(0xFFFA6FFF),
        route = homeScreenRoute
    )

    data object MoneyRequests : BottomBarTab(
        title = "Requests",
        icon = Icons.Rounded.Refresh,
        color = Color(0xFF7B8DFF),
        route = moneyRequestsScreenRoute
    )

    data object Settings : BottomBarTab(
        title = "Settings",
        icon = Icons.Rounded.Settings,
        color = Color(0xFFADFF64),
        route = settingsScreenRoute
    )
}

val tabs = listOf(
    BottomBarTab.Home,
    BottomBarTab.MoneyRequests,
    BottomBarTab.Profile,
    BottomBarTab.Settings
)

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomBarTab.Home,
        BottomBarTab.Profile,
        BottomBarTab.MoneyRequests,
        BottomBarTab.Settings
    )
    val unSelectedColor = Color(0xFF6E6E6E)
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        items.forEach { screen ->
            val selected = currentRoute == screen.route
            val selectedLabelColor = if (selected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                unSelectedColor
            }
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 12.sp,
                        color = selectedLabelColor,
                    )
                },
                selected = selected,
                selectedContentColor = selectedLabelColor,
                unselectedContentColor = unSelectedColor,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(homeScreenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
