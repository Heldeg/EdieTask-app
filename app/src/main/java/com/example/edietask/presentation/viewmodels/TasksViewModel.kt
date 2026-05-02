package com.example.edietask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edietask.domain.model.Task
import com.example.edietask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.edietask.domain.model.Priority

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

    fun addTask(name: String, description: String, priority: Priority) {
        viewModelScope.launch {
            taskRepository.insertTask(
                Task(
                    name = name,
                    description = description,
                    priority = priority,
                    createdAt = System.currentTimeMillis(),
                    listId = listId
                )
            )
        }
    }
}
