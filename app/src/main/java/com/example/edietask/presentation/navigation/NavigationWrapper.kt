package com.example.edietask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.edietask.presentation.screens.HomeScreen
import com.example.edietask.presentation.screens.TasksScreen
import com.example.edietask.presentation.viewmodels.AppViewModelProvider
import kotlinx.serialization.Serializable

// Definición de las rutas
@Serializable
object Home

@Serializable
data class Tasks(val listId: Int, val listTitle: String)

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                viewModel = viewModel(factory = AppViewModelProvider.Factory),
                onListClick = { listId, title ->
                    navController.navigate(Tasks(listId, title))
                }
            )
        }

        composable<Tasks> { backStackEntry ->
            val tasksRoute: Tasks = backStackEntry.toRoute()
            TasksScreen(
                viewModel = viewModel(factory = AppViewModelProvider.provideTasksViewModelFactory(tasksRoute.listId)),
                listTitle = tasksRoute.listTitle,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
