package com.example.edietask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edietask.domain.model.TaskList
import com.example.edietask.domain.repository.ListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val listRepository: ListRepository
) : ViewModel() {
    val taskLists: StateFlow<List<TaskList>> = listRepository.getAllList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
