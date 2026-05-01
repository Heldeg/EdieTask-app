package com.example.edietask.domain.data.local.repository

import com.example.edietask.domain.data.local.dao.CategoryDao
import com.example.edietask.domain.data.local.mapper.toDomain
import com.example.edietask.domain.data.local.mapper.toEntity
import com.example.edietask.domain.model.Category
import com.example.edietask.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map() {
            list -> list.map { it.toDomain()}
        }
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category.toEntity())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category.toEntity())
    }
}