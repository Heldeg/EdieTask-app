package com.example.edietask.domain.model

data class TaskList (
    val id: Int? = null,
    val title: String,
    val description: String,
    val color: String,
    val categoryId: Int
)