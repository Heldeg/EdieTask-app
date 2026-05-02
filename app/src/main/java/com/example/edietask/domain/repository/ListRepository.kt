package com.example.edietask.domain.repository

import com.example.edietask.domain.model.TaskList
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    fun getAllList(): Flow<List<TaskList>>
    fun getListByCategory(categoryId: Int): Flow<List<TaskList>>
    suspend fun getListById(id: Int): TaskList
    suspend fun insertList(taskList: TaskList)
    suspend fun deleteList(taskList: TaskList)
    suspend fun updateList(taskList: TaskList)
}