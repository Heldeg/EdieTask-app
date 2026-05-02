package com.example.edietask.presentation.components

import androidx.compose.foundation.layout.padding
import com.example.edietask.ui.theme.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.edietask.domain.model.Priority

@Composable
fun PriorityChip(
    priority: Priority,
    modifier: Modifier = Modifier
) {
    // Colores más sutiles y profesionales (tonos pastel/desaturados)
    val (containerColor, contentColor) = when (priority) {
        Priority.LOW -> PriorityLowBg to PriorityLowText    // Verde suave
        Priority.MEDIUM -> PriorityMediumBg to PriorityMediumText // Naranja/Ambar suave
        Priority.HIGH -> PriorityHighBg to PriorityHighText   // Rojo suave
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
