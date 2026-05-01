package com.example.edietask.domain.data.local.mapper

import com.example.edietask.domain.data.local.entity.TaskEntity
import com.example.edietask.domain.model.Priority
import com.example.edietask.domain.model.Task

fun TaskEntity.toDomain(): Task = Task (
    id = id,
    name = name,
    description = description,
    isCompleted = isCompleted,
    priority = Priority.fromInt(priority),
    createdAt = createdAt,
    deletedAt = deletedAt,
    listId = listId,
    parentTaskId = parentTaskId
)

fun Task.toEntity(): TaskEntity = TaskEntity (
    id = id,
    name = name,
    description = description,
    isCompleted = isCompleted,
    priority = priority.priority,
    createdAt = createdAt,
    deletedAt = deletedAt,
    listId = listId,
    parentTaskId = parentTaskId
)