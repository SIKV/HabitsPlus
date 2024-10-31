package com.github.sikv.habitsplus.ui.feature.todos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.sikv.habitsplus.ui.TodosRoute
import com.github.sikv.habitsplus.ui.feature.addtodo.AddTodoRoute
import com.github.sikv.habitsplus.ui.feature.addtodo.AddTodoScreen

@Composable
fun TodosNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TodosRoute
    ) {
        composable<TodosRoute> {
            TodosScreen(
                onNavigateToAddTodo = {
                    navController.navigate(AddTodoRoute)
                }
            )
        }
        composable<AddTodoRoute> {
            AddTodoScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
