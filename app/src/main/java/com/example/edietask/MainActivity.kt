package com.example.edietask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.edietask.domain.data.local.database.AppDatabase
import com.example.edietask.domain.data.local.repository.CategoryRepositoryImpl
import com.example.edietask.domain.data.local.repository.ListRepositoryImpl
import com.example.edietask.domain.data.local.repository.TaskRepositoryImpl
import com.example.edietask.domain.util.seedDatabase
import com.example.edietask.ui.theme.EDieTaskTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //TODO: MOVE to a view
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
        //END seed database

        setContent {
            EDieTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EDieTaskTheme {
        Greeting("Android")
    }
}