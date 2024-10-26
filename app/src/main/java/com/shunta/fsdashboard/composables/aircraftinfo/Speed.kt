package com.shunta.fsdashboard.composables.aircraftinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shunta.fsdashboard.composables.generics.Gauge

@Composable
fun Speed(
    speedTitle: String,
    currentSpeed: Int,
    maxSpeed: Float,
    size: Int = 120,
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = speedTitle,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "${currentSpeed}kts",
        )
        val speedPerc = currentSpeed / maxSpeed
        Gauge(
            inputValue = speedPerc,
            progressColors = listOf(
                Color(0xFF006300),
                Color.Yellow,
                Color.Yellow,
                Color.Red
            ),
            sizeModifier =  size,
        )
    }
}