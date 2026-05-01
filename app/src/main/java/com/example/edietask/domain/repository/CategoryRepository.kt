package com.example.edietask.domain.repository

import com.example.edietask.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
     fun getAllCategories(): Flow<List<Category>>
     suspend fun insertCategory(category: Category)
     suspend fun deleteCategory(category: Category)
     suspend fun updateCategory(category: Category)
}