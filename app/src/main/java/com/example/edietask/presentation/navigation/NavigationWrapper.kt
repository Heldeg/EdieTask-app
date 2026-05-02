package com.example.edietask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.edietask.presentation.screens.HomeScreen
import com.example.edietask.presentation.screens.TasksScreen
import com.example.edietask.presentation.screens.AddListScreen
import com.example.edietask.presentation.screens.AddTaskScreen
import com.example.edietask.presentation.viewmodels.AppViewModelProvider
import com.example.edietask.presentation.viewmodels.HomeViewModel
import com.example.edietask.presentation.viewmodels.TasksViewModel
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object AddList

@Serializable
data class Tasks(val listId: Int, val listTitle: String)

@Serializable
data class AddTask(val listId: Int)

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                viewModel = viewModel(factory = AppViewModelProvider.Factory),
                onListClick = { listId, title ->
                    navController.navigate(Tasks(listId, title))
                },
                onAddListClick = {
                    navController.navigate(AddList)
                }
            )
        }

        composable<AddList> {
            val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            AddListScreen(
                onBack = { navController.popBackStack() },
                onSave = { title, description ->
                    homeViewModel.insertList(title, description)
                    navController.popBackStack()
                }
            )
        }

        composable<Tasks> { backStackEntry ->
            val tasksRoute: Tasks = backStackEntry.toRoute()
            TasksScreen(
                viewModel = viewModel(factory = AppViewModelProvider.provideTasksViewModelFactory(tasksRoute.listId)),
                listTitle = tasksRoute.listTitle,
                onBack = { navController.popBackStack() },
                onAddTaskClick = {
                    navController.navigate(AddTask(tasksRoute.listId))
                }
            )
        }

        composable<AddTask> { backStackEntry ->
            val addTaskRoute: AddTask = backStackEntry.toRoute()
            val tasksViewModel: TasksViewModel = viewModel(
                factory = AppViewModelProvider.provideTasksViewModelFactory(addTaskRoute.listId)
            )
            AddTaskScreen(
                onBack = { navController.popBackStack() },
                onSave = { name, description, priority ->
                    tasksViewModel.addTask(name, description, priority)
                    navController.popBackStack()
                }
            )
        }
    }
}
