package fergaral.tidesapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
                    Text(style = MaterialTheme.typography.h2, text = currentScreen?.let { stringResource(it.resourceId) } ?: "", modifier = Modifier.padding(16.dp))
                    NavHost(navController = navController, startDestination = Screen.Ports.route) {
                        composable(Screen.Ports.route) {
                            Ports()
                        }
                        composable(Screen.Favorites.route) {}
                        composable(Screen.Tides.route) {}
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Ports() {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 180.dp)
        ) {
            items(20) {
                Card(modifier = Modifier.padding(16.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painterResource(R.drawable.img), null, contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxWidth())
                        Text("Navia", modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}