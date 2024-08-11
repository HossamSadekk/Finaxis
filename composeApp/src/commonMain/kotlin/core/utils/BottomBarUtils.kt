package core.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import finaxis.composeapp.generated.resources.Res
import finaxis.composeapp.generated.resources.ic_home
import finaxis.composeapp.generated.resources.ic_money_request
import finaxis.composeapp.generated.resources.ic_settings
import finaxis.composeapp.generated.resources.ic_transactions
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import presentation.MoneyRequests.nav.moneyRequestsScreenRoute
import presentation.home.nav.homeScreenRoute
import presentation.settings.nav.settingsScreenRoute

sealed class BottomBarTab(val title: String, val iconResId: DrawableResource, val color: Color, val route: String) {
    data object Profile : BottomBarTab(
        title = "Transaction",
        iconResId = Res.drawable.ic_transactions,
        color = Color(0xFFFFA574),
        route = ""
    )

    data object Home : BottomBarTab(
        title = "Home",
        iconResId = Res.drawable.ic_home,
        color = Color(0xFFFA6FFF),
        route = homeScreenRoute
    )

    data object MoneyRequests : BottomBarTab(
        title = "Requests",
        iconResId = Res.drawable.ic_money_request,
        color = Color(0xFF7B8DFF),
        route = moneyRequestsScreenRoute
    )

    data object Settings : BottomBarTab(
        title = "Settings",
        iconResId = Res.drawable.ic_settings,
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
        BottomBarTab.MoneyRequests,
        BottomBarTab.Profile,
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
                        imageVector = vectorResource(screen.iconResId),
                        contentDescription = screen.title,
                        modifier = Modifier.size(24.dp)
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
