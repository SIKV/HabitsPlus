package com.github.sikv.habitsplus.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.sikv.habitsplus.ui.feature.activity.ActivityScreen
import com.github.sikv.habitsplus.ui.feature.habits.HabitsScreen
import com.github.sikv.habitsplus.ui.feature.more.MoreScreen
import com.github.sikv.habitsplus.ui.feature.todos.TodosScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ComposeApp() {
    val navController = rememberNavController()

    MaterialTheme {
        Scaffold(
            bottomBar = { Navigation(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HabitsRoute,
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                composable<HabitsRoute> {
                    HabitsScreen()
                }
                composable<TodosRoute> {
                    TodosScreen()
                }
                composable<ActivityRoute> {
                    ActivityScreen()
                }
                composable<MoreRoute> {
                    MoreScreen()
                }
            }
        }
    }
}

@Composable
private fun Navigation(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        topLevelRoutes.forEach { topLevelRoute ->
            NavigationBarItem(
                icon = { Icon(topLevelRoute.icon, contentDescription = stringResource(topLevelRoute.name)) },
                label = { Text(stringResource(topLevelRoute.name)) },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
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
