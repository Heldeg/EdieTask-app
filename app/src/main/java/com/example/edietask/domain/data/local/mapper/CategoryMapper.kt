package com.example.edietask.domain.data.local.mapper

import com.example.edietask.domain.data.local.entity.CategoryEntity
import com.example.edietask.domain.model.Category

fun CategoryEntity.toDomain(): Category = Category(
    id = id,
    name = name,
    emoji = emoji
)

fun Category.toEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
    emoji = emoji
)