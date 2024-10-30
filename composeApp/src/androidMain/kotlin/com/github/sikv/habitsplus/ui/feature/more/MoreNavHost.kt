package com.github.sikv.habitsplus.ui.feature.more

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.sikv.habitsplus.ui.MoreRoute

@Composable
fun MoreNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MoreRoute
    ) {
        composable<MoreRoute> {
            MoreScreen()
        }
    }
}
