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
import kotlinx.coroutines.flow.first
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
            val existingCategories = categoryRepo.getAllCategories().first()

            if (existingCategories.isEmpty()) {
                Log.d("EDIeTask_DB", "--- Base de datos vacía. Iniciando Seed ---")
                seedDatabase(
                    categoryRepo = categoryRepo,
                    listRepo = listRepo,
                    taskRepo = taskRepo
                )
                Log.d("EDIeTask_DB", "Datos iniciales insertados correctamente.")
            } else {
                Log.d("EDIeTask_DB", "La base de datos ya tiene datos. Omitiendo Seed.")
                Log.d("EDIeTask_DB", "Categorías actuales: ${existingCategories.size}")
            }
        }

        setContent {
            EDieTaskTheme {
                NavigationWrapper()
            }
        }
    }
}
