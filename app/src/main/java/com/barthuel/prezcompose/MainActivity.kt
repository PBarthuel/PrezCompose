package com.barthuel.prezcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.barthuel.prezcompose.ui.theme.PrezComposeTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrezComposeTheme {
                val navController = rememberAnimatedNavController()
                val bottomNavigationItems = listOf(
                    Destinations.MainBottomBarNavigation.Home,
                    Destinations.MainBottomBarNavigation.Infos,
                    Destinations.MainBottomBarNavigation.Profile
                )

                Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                    AnimatedNavHost(
                        modifier = Modifier.weight(1f),
                        navController = navController,
                        startDestination = Destinations.MainBottomBarNavigation.Home.root,
                        enterTransition = { openFromRight() },
                        exitTransition = { fadeOut(animationSpec = tween(300)) },
                        popEnterTransition = { openFromRight() },
                        popExitTransition = { fadeOut(animationSpec = tween(300)) }
                    ) {
                        composable(Destinations.MainBottomBarNavigation.Home.root) {
                            HomeScreen()
                        }
                        composable(Destinations.MainBottomBarNavigation.Infos.root) {
                            InfosScreen()
                        }
                        composable(Destinations.MainBottomBarNavigation.Profile.root) {
                            ProfileScreen()
                        }
                    }

                    BottomNavigation {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        bottomNavigationItems.forEach { screen ->
                            BottomNavigationItem(
                                icon = { Icon(screen.icon, contentDescription = null) },
                                label = { Text(stringResource(id = screen.resourceId)) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.root } == true,
                                onClick = {
                                    navController.navigate(screen.root) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

object Destinations {
    sealed class MainBottomBarNavigation(
        val root: String,
        @StringRes val resourceId: Int,
        val icon: ImageVector
    ) {
        object Home : MainBottomBarNavigation("home", R.string.home, Icons.Filled.Home)
        object Infos : MainBottomBarNavigation("infos", R.string.infos, Icons.Filled.Favorite)
        object Profile :
            MainBottomBarNavigation("profile", R.string.profile, Icons.Filled.Person)
    }
}

@ExperimentalAnimationApi
fun AnimatedContentScope<*>.openFromRight(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentScope.SlideDirection.Left,
        animationSpec = tween(300)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PrezComposeTheme {

    }
}