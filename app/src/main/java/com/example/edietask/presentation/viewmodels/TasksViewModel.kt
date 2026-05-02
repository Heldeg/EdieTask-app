package com.example.edietask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edietask.domain.model.Task
import com.example.edietask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TasksViewModel(
    private val taskRepository: TaskRepository,
    private val listId: Int
) : ViewModel() {

    val tasks: StateFlow<List<Task>> = taskRepository.getActiveTasksForList(listId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
