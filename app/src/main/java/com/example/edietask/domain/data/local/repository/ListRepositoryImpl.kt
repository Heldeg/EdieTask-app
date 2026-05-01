package com.example.edietask.domain.data.local.repository

import com.example.edietask.domain.data.local.dao.ListDao
import com.example.edietask.domain.data.local.mapper.toDomain
import com.example.edietask.domain.data.local.mapper.toEntity
import com.example.edietask.domain.model.TaskList
import com.example.edietask.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ListRepositoryImpl(private val listDao: ListDao) : ListRepository {
    override fun getAllList(): Flow<List<TaskList>> {
        return listDao.getAllList().map {
            list -> list.map { it.toDomain() }
        }
    }
    override fun getListByCategory(categoryId: Int): Flow<List<TaskList>> {
        return listDao.getListByCategory(categoryId).map {
                list -> list.map { it.toDomain() }
        }
    }

    override suspend fun getListById(id: Int): TaskList {
        return listDao.getListById(id).toDomain()
    }

    override suspend fun insertList(taskList: TaskList) {
        return listDao.insertList(taskList.toEntity())
    }

    override suspend fun deleteList(taskList: TaskList) {
        return listDao.deleteList(taskList.toEntity())
    }
}