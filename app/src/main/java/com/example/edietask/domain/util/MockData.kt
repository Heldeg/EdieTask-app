package com.example.edietask.domain.util

import com.example.edietask.domain.model.Category
import com.example.edietask.domain.model.Priority
import com.example.edietask.domain.model.Task
import com.example.edietask.domain.model.TaskList
import com.example.edietask.domain.repository.CategoryRepository
import com.example.edietask.domain.repository.ListRepository
import com.example.edietask.domain.repository.TaskRepository

object MockData {

    val categories = listOf(
        Category(id = 1, name = "General", emoji = "📌"),
        Category(id = 2, name = "Proyectos", emoji = "🚀"),
        Category(id = 3, name = "Bienestar", emoji = "🧘‍♂️")
    )

    val taskLists = listOf(
        TaskList(
            id = 1,
            title = "Universidad",
            description = "Tareas de la unviersidad",
            color = "#FF9800", // Un naranja vibrante para llamar la atención
            categoryId = 1
        ),
        TaskList(
            id = 2,
            title = "Trabajo",
            description = "Tareas importantes del día",
            color = "#2196F3", // Azul
            categoryId = 1
        )
    )

    val tasks = listOf(
        Task(
            id = 1,
            name = "Primera tarea ✍️",
            description = "Toca el botón '+' para agregar una nueva tarea a esta lista.",
            priority = Priority.HIGH,
            createdAt = System.currentTimeMillis(),
            listId = 1,
            isCompleted = false
        ),
        Task(
            id = 2,
            name = "Desliza para borrar 🗑️",
            description = "Intenta deslizar esta tarea hacia un lado para eliminarla.",
            priority = Priority.MEDIUM,
            createdAt = System.currentTimeMillis(),
            listId = 1,
            isCompleted = false
        ),
        Task(
            id = 3,
            name = "Marca esta tarea como completada ✅",
            description = "Toca el círculo a la izquierda para terminar esta tarea.",
            priority = Priority.LOW,
            createdAt = System.currentTimeMillis(),
            listId = 1,
            isCompleted = false
        ),

        Task(
            id = 4,
            name = "Revisar agenda",
            description = "Organizar las reuniones de la semana",
            priority = Priority.HIGH,
            createdAt = System.currentTimeMillis(),
            listId = 2,
            isCompleted = true // Esta ya aparece completada como ejemplo
        ),
        Task(
            id = 5,
            name = "Beber 2 litros de agua",
            description = "Mantenerse hidratado es importante",
            priority = Priority.MEDIUM,
            createdAt = System.currentTimeMillis(),
            listId = 2,
            isCompleted = false
        )
    )
}

suspend fun seedDatabase(
    categoryRepo: CategoryRepository,
    listRepo: ListRepository,
    taskRepo: TaskRepository
) {
    MockData.categories.forEach { categoryRepo.insertCategory(it) }
    MockData.taskLists.forEach { listRepo.insertList(it) }
    MockData.tasks.forEach { taskRepo.insertTask(it) }
}