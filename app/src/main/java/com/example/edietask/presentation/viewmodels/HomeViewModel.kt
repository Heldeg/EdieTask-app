package com.example.edietask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edietask.domain.model.TaskList
import com.example.edietask.domain.repository.ListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listRepository: ListRepository
) : ViewModel() {
    val taskLists: StateFlow<List<TaskList>> = listRepository.getAllList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertList(title: String, description: String, color: String) {
        viewModelScope.launch {
            listRepository.insertList(
                TaskList(
                    title = title,
                    description = description,
                    color = color,
                    categoryId = 1 // Valor por defecto para simplificar
                )
            )
        }
    }
}
