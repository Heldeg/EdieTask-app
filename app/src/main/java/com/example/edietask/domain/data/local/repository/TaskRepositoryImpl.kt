package com.example.edietask.domain.data.local.repository

import com.example.edietask.domain.data.local.dao.TaskDao
import com.example.edietask.domain.data.local.mapper.toDomain
import com.example.edietask.domain.data.local.mapper.toEntity
import com.example.edietask.domain.model.Task
import com.example.edietask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    override fun getActiveTasksForList(listId: Int): Flow<List<Task>> {
        return taskDao.getActiveTasksForList(listId).map {
            list -> list.map { it.toDomain() }
        }
    }

    override fun getActiveSubTasks(parentTaskId: Int): Flow<List<Task>> {
        return taskDao.getActiveSubTasks(parentTaskId).map {
            list -> list.map { it.toDomain() }
        }
    }

    override suspend fun getTaskById(id: Int): Task {
        return taskDao.getTaskById(id).toDomain()
    }

    override suspend fun insertTask(task: Task) {
        return taskDao.insertTask(task.toEntity())
    }

    override suspend fun updateTask(task: Task) {
        return taskDao.updateTask(task.toEntity())
    }

    override suspend fun deleteTask(taskId: Int, timestamp: Long) {
        return taskDao.deleteTask(taskId, timestamp)
    }

    override suspend fun changeTaskStatus(taskId: Int) {
        return taskDao.changeTaskStatus(taskId)
    }
}