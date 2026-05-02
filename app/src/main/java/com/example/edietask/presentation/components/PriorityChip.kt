package com.example.edietask.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
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
    val color = when (priority) {
        Priority.LOW -> Color(0xFF4CAF50)
        Priority.MEDIUM -> Color(0xFFFFC107)
        Priority.HIGH -> Color(0xFFF44336)
    }

    AssistChip(
        onClick = { },
        label = { 
            Text(
                text = priority.name,
                style = androidx.compose.material3.MaterialTheme.typography.labelSmall
            ) 
        },
        modifier = modifier.padding(horizontal = 4.dp),
        colors = AssistChipDefaults.assistChipColors(
            labelColor = color
        ),
        border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
    )
}
