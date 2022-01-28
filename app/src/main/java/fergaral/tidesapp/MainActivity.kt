package fergaral.tidesapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import fergaral.tidesapp.ui.navigation.Screen
import fergaral.tidesapp.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                TidesApp()
            }
        }
    }

    @Composable
    fun TidesApp() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight
        val backgroundColor = MaterialTheme.colors.background

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = backgroundColor,
                darkIcons = useDarkIcons
            )
        }

        val navController = rememberNavController()
        val screens = listOf(Screen.Favorites, Screen.Tides, Screen.Ports)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val currentDestination = navBackStackEntry?.destination
                    screens.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(painter = painterResource(screen.navIconRes), contentDescription = null) },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(innerPadding)) {
                Column {
                    val currentScreen = screens.find { it.route == navBackStackEntry?.destination?.route }
                    Text(style = MaterialTheme.typography.h2, text = currentScreen?.let { stringResource(it.resourceId) } ?: "")
                    NavHost(navController = navController, startDestination = Screen.Ports.route) {
                        composable(Screen.Ports.route) {}
                        composable(Screen.Favorites.route) {}
                        composable(Screen.Tides.route) {}
                    }
                }
            }
        }
    }
}