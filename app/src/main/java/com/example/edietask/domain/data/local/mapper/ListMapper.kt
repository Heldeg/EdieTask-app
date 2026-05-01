package com.example.edietask.domain.data.local.mapper

import com.example.edietask.domain.data.local.entity.ListEntity
import com.example.edietask.domain.model.TaskList

fun ListEntity.toDomain(): TaskList = TaskList (
    id = id,
    title = title,
    description = description,
    color = color,
    categoryId = categoryId
)

fun TaskList.toEntity(): ListEntity = ListEntity (
    id = id,
    title = title,
    description = description,
    color = color,
    categoryId = categoryId
)