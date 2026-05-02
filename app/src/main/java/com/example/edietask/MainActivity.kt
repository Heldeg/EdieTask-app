package com.example.edietask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.edietask.domain.data.local.database.AppDatabase
import com.example.edietask.domain.data.local.repository.CategoryRepositoryImpl
import com.example.edietask.domain.data.local.repository.ListRepositoryImpl
import com.example.edietask.domain.data.local.repository.TaskRepositoryImpl
import com.example.edietask.domain.util.seedDatabase
import com.example.edietask.presentation.navigation.NavigationWrapper
import com.example.edietask.ui.theme.EDieTaskTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Seed Database
        val database = AppDatabase.getInstance(applicationContext)

        val categoryRepo = CategoryRepositoryImpl(database.categoryDao())
        val listRepo = ListRepositoryImpl(database.listDao())
        val taskRepo = TaskRepositoryImpl(database.taskDao())

        lifecycleScope.launch {
            Log.d("EDIeTask_DB", "--- Iniciando prueba de Base de Datos ---")

            seedDatabase(
                categoryRepo = categoryRepo,
                listRepo = listRepo,
                taskRepo = taskRepo
            )

            Log.d("EDIeTask_DB", "Datos insertados correctamente.")

            categoryRepo.getAllCategories().collect { categories ->
                Log.d("EDIeTask_DB", "Categorías en DB: ${categories.size}")
                categories.forEach { Log.d("EDIeTaskDB", " -> ${it.name} ${it.emoji}") }

            }
        }

        setContent {
            EDieTaskTheme {
                NavigationWrapper()
            }
        }
    }
}
