package com.example.edietask.domain.repository

import com.example.edietask.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getActiveTasksForList(listId: Int): Flow<List<Task>>
    fun getActiveSubTasks(parentTaskId: Int): Flow<List<Task>>

    suspend fun getTaskById(id:Int): Task
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(taskId: Int, timestamp: Long = System.currentTimeMillis())
}