package com.example.edietask.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.edietask.R
import androidx.compose.ui.unit.dp
import com.example.edietask.domain.model.Priority
import com.example.edietask.presentation.viewmodels.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TasksViewModel,
    taskId: Int?=null,
    onBack: () -> Unit,
    onSave: (String, String, Priority) -> Unit,
    onUpdate: (Int, String, String, Priority) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(Priority.MEDIUM) }
    var isEditing by remember { mutableStateOf(false) }

    //Check if it is editing and get task
    LaunchedEffect(taskId) {
        if (taskId != null) {
            isEditing = true
            val task = viewModel.getTask(taskId)
            name = task.name
            description = task.description
            selectedPriority = task.priority
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_new_task)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.desc_back))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.label_task_name)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.label_description)) },
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(stringResource(R.string.label_priority), style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Priority.entries.forEach { priority ->
                    FilterChip(
                        selected = selectedPriority == priority,
                        onClick = { selectedPriority = priority },
                        label = { Text(priority.name) }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if (!isEditing) {
                Button(
                    onClick = { onSave(name, description, selectedPriority) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = name.isNotBlank()
                ) {
                    Text(stringResource(R.string.btn_save_task))
                }
            } else {
                Button(
                    onClick = { onUpdate(taskId!!, name, description, selectedPriority) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = name.isNotBlank()
                ) {
                    Text(stringResource(R.string.btn_update_task))
                }
            }


        }
    }
}
