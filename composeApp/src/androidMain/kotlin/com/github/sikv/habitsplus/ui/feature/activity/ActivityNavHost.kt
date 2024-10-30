package com.github.sikv.habitsplus.ui.feature.activity

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.sikv.habitsplus.ui.ActivityRoute

@Composable
fun ActivityNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ActivityRoute
    ) {
        composable<ActivityRoute> {
            ActivityScreen()
        }
    }
}
