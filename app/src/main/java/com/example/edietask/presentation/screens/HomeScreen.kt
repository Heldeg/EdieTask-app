package com.example.edietask.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.edietask.presentation.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onListClick: (Int, String) -> Unit,
) {
    val taskLists by viewModel.taskLists.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Mis Listas") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(taskLists) { list ->
                ListItem(
                    modifier = Modifier.clickable { 
                        onListClick(list.id ?: 0, list.title) 
                    },
                    headlineContent = { Text(list.title) },
                    supportingContent = { Text(list.description) }
                )
            }
            
            if (taskLists.isEmpty()) {
                item {
                    Text(
                        text = "No tienes listas aún.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
