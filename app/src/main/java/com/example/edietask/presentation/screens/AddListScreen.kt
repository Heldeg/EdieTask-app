package com.example.edietask.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.edietask.R
import com.example.edietask.presentation.components.MainActionButton
import com.example.edietask.ui.theme.ListColorPalette
import com.example.edietask.ui.theme.toHexCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListScreen(
    onBack: () -> Unit,
    onSave: (String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    var selectedColor by remember { mutableStateOf(ListColorPalette[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_new_list)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
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
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.label_title)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.label_description)) },
                modifier = Modifier.fillMaxWidth()
            )

            Text(stringResource(R.string.label_color), style = MaterialTheme.typography.titleMedium)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ListColorPalette.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color)
                            .clickable { selectedColor = color }
                            .border(
                                width = if (selectedColor == color) 3.dp else 0.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            MainActionButton(
                text = stringResource(R.string.btn_save_list),
                onClick = { onSave(title, description, selectedColor.toHexCode()) },
                enabled = title.isNotBlank()
            )
        }
    }
}
