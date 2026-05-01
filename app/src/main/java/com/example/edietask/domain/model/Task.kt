package com.example.edietask.domain.model

enum class Priority(val priority: Int) {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    companion object {
        fun fromInt(priority: Int): Priority {
            return Priority.entries.find { it.priority == priority } ?: MEDIUM
        }
    }
}

data class Task(
    val id: Int? = null,
    val name: String,
    val description: String,
    val isCompleted: Boolean = false,
    val priority: Priority,
    val createdAt: Long,
    val deletedAt: Long? = null,
    val listId: Int,
    val parentTaskId: Int? = null
)
