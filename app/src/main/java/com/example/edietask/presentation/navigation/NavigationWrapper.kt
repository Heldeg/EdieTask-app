package com.example.edietask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.edietask.presentation.screens.WelcomeScreen
import com.example.edietask.presentation.screens.HomeScreen
import com.example.edietask.presentation.screens.TasksScreen
import com.example.edietask.presentation.screens.AddListScreen
import com.example.edietask.presentation.screens.AddTaskScreen
import com.example.edietask.presentation.viewmodels.AppViewModelProvider
import com.example.edietask.presentation.viewmodels.HomeViewModel
import com.example.edietask.presentation.viewmodels.TasksViewModel
import kotlinx.serialization.Serializable

@Serializable
object Welcome

@Serializable
object Home

@Serializable
data class AddEditList(val listId: Int? = null)

@Serializable
data class Tasks(val listId: Int, val listTitle: String)

@Serializable
data class AddEditTask(val listId: Int, val taskId: Int? = null)

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Welcome) {
        composable<Welcome> {
            WelcomeScreen(
                onStartClick = {
                    navController.navigate(Home) {
                        popUpTo(Welcome) { inclusive = true }
                    }
                }
            )
        }

        composable<Home> {
            HomeScreen(
                viewModel = viewModel(factory = AppViewModelProvider.Factory),
                onListClick = { listId, title ->
                    navController.navigate(Tasks(listId, title))
                },
                onAddListClick = {
                    navController.navigate(AddEditList())
                }
            )
        }

        composable<AddEditList> { backStackEntry ->
            val route: AddEditList = backStackEntry.toRoute()
            val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            AddListScreen(
                viewModel = homeViewModel,
                listId = route.listId,
                onBack = { navController.popBackStack() },
                onSave = { title, description, color ->
                    homeViewModel.insertList(title, description, color)
                    navController.popBackStack()
                },
                onUpdate = { id, title, description, color ->
                    homeViewModel.updateList(id, title, description, color)
                    navController.navigate(Home) {
                        popUpTo(Home) { inclusive = true }
                    }
                }
            )
        }

        composable<Tasks> { backStackEntry ->
            val tasksRoute: Tasks = backStackEntry.toRoute()
            TasksScreen(
                viewModel = viewModel(factory = AppViewModelProvider.provideTasksViewModelFactory(tasksRoute.listId)),
                listTitle = tasksRoute.listTitle,
                onBack = { navController.popBackStack() },
                onEditListClick = {
                    navController.navigate(AddEditList(tasksRoute.listId))
                },
                onAddTaskClick = {
                    navController.navigate(AddEditTask(tasksRoute.listId))
                },
                onEditTaskClick = {taskId ->
                    navController.navigate(AddEditTask(tasksRoute.listId, taskId))
                }
            )
        }

        composable<AddEditTask> { backStackEntry ->
            val addTaskRoute: AddEditTask = backStackEntry.toRoute()
            val tasksViewModel: TasksViewModel = viewModel(
                factory = AppViewModelProvider.provideTasksViewModelFactory(addTaskRoute.listId)
            )
            AddTaskScreen(
                viewModel = tasksViewModel,
                taskId = addTaskRoute.taskId,
                onBack = { navController.popBackStack() },
                onSave = { name, description, priority ->
                    tasksViewModel.addTask(name, description, priority)
                    navController.popBackStack()
                },
                onUpdate = { id, name, description, priority ->
                    tasksViewModel.updateTask(id, name, description, priority)
                    navController.popBackStack()
                }
            )
        }
    }
}
