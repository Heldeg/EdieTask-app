package com.example.edietask.presentation.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.edietask.domain.data.local.database.AppDatabase

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val database = AppDatabase.getInstance(
                (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as android.app.Application).applicationContext
            )
            HomeViewModel(
                listRepository = com.example.edietask.domain.data.local.repository.ListRepositoryImpl(database.listDao())
            )
        }
    }

    fun provideTasksViewModelFactory(listId: Int) = viewModelFactory {
        initializer {
            val database = AppDatabase.getInstance(
                (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as android.app.Application).applicationContext
            )
            TasksViewModel(
                taskRepository = com.example.edietask.domain.data.local.repository.TaskRepositoryImpl(database.taskDao()),
                listId = listId
            )
        }
    }
}
