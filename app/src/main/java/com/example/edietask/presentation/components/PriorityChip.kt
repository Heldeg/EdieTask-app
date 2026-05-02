package com.example.edietask.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.edietask.domain.model.Priority

@Composable
fun PriorityChip(
    priority: Priority,
    modifier: Modifier = Modifier
) {
    // Colores más sutiles y profesionales (tonos pastel/desaturados)
    val (containerColor, contentColor) = when (priority) {
        Priority.LOW -> Color(0xFFE8F5E9) to Color(0xFF2E7D32)    // Verde suave
        Priority.MEDIUM -> Color(0xFFFFF3E0) to Color(0xFFEF6C00) // Naranja/Ambar suave
        Priority.HIGH -> Color(0xFFFFEBEE) to Color(0xFFC62828)   // Rojo suave
    }

    Surface(
        color = containerColor,
        contentColor = contentColor,
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = priority.name,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
