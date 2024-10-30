package com.github.sikv.habitsplus.ui.feature.habits

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.sikv.habitsplus.ui.HabitsRoute

@Composable
fun HabitsNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HabitsRoute
    ) {
        composable<HabitsRoute> {
            HabitsScreen()
        }
    }
}
