package com.github.sikv.habitsplus.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.github.sikv.habitsplus.ui.feature.activity.add.AddActivityRoute
import com.github.sikv.habitsplus.ui.feature.activity.add.addActivityDestination
import com.github.sikv.habitsplus.ui.feature.activity.list.ActivityRoute
import com.github.sikv.habitsplus.ui.feature.activity.list.activityDestination
import com.github.sikv.habitsplus.ui.feature.addtodo.AddTodoRoute
import com.github.sikv.habitsplus.ui.feature.addtodo.addTodoDestination
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenuItem
import com.github.sikv.habitsplus.ui.feature.habits.HabitsRoute
import com.github.sikv.habitsplus.ui.feature.habits.habitsDestination
import com.github.sikv.habitsplus.ui.feature.more.MoreRoute
import com.github.sikv.habitsplus.ui.feature.more.moreDestination
import com.github.sikv.habitsplus.ui.feature.todos.TodosRoute
import com.github.sikv.habitsplus.ui.feature.todos.todosDestination
import com.github.sikv.habitsplus.ui.theme.LocalSpacing
import com.github.sikv.habitsplus.ui.theme.Spacing
import com.github.sikv.habitsplus.util.DateTimeFormatter
import com.github.sikv.habitsplus.util.LocalDateTimeFormatter
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ComposeApp() {
    val navController = rememberNavController()

    val onMenuItemClick: (ScaffoldMenuItem) -> Unit = { menuItem ->
        when (menuItem) {
            ScaffoldMenuItem.ADD_HABIT -> {
                // TODO: Implement.
            }
            ScaffoldMenuItem.ADD_TODO -> navController.navigate(AddTodoRoute)
            ScaffoldMenuItem.ADD_ACTIVITY -> navController.navigate(AddActivityRoute)
        }
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalDateTimeFormatter provides DateTimeFormatter()
    ) {
        Scaffold(
            bottomBar = { Navigation(navController) },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HabitsRootRoute,
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            ) {
                navigation<HabitsRootRoute>(startDestination = HabitsRoute) {
                    habitsDestination(
                        onMenuItemClick = onMenuItemClick
                    )
                }
                navigation<TodosRootRoute>(startDestination = TodosRoute) {
                    todosDestination(
                        onMenuItemClick = onMenuItemClick
                    )
                    addTodoDestination(
                        onNavigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
                navigation<ActivityRootRoute>(startDestination = ActivityRoute) {
                    activityDestination(
                        onMenuItemClick = onMenuItemClick
                    )
                    addActivityDestination(
                        onNavigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
                navigation<MoreRootRoute>(startDestination = MoreRoute) {
                    moreDestination()
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
                icon = { Icon(topLevelRoute.icon, contentDescription = stringResource(topLevelRoute.title)) },
                label = { Text(stringResource(topLevelRoute.title)) },
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
