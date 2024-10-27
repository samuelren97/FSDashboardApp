package com.shunta.fsdashboard.composables.generics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun Compass(
    angle: Float,
    modifier: Modifier = Modifier.size(200.dp)
) {
    Column (modifier = modifier) {
        var circleColor = Color.Black
        var textColor = android.graphics.Color.BLACK
        if (isSystemInDarkTheme()) {
            circleColor = Color.White
            textColor = android.graphics.Color.WHITE
        }
        Text(
            "${String.format(Locale.US,"%.2f", angle)}°",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight(800),
            textAlign = TextAlign.End
        )

        Canvas(modifier = modifier.padding(10.dp)) {
            // Draw the outer circle
            drawCircle(
                color = circleColor,
                style = Stroke(width = 8f)
            )

            // Draw the N, E, S, W labels
            val directions = listOf("N", "E", "S", "W")
            val directionPositions = listOf(
                Offset(size.width / 2, 40f),
                Offset(size.width - 60f, size.height / 2),
                Offset(size.width / 2, size.height - 10f),
                Offset(60f, size.height / 2)
            )

            directions.zip(directionPositions).forEach { (label, position) ->
                drawContext.canvas.nativeCanvas.apply {
                    drawText(label, position.x, position.y, android.graphics.Paint().apply {
                        color = textColor
                        textSize = 40f
                        textAlign = android.graphics.Paint.Align.CENTER
                    })
                }
            }

            // Draw rotation needle
            rotate(degrees = angle, pivot = Offset(size.width / 2, size.height / 2)) {
                drawLine(
                    color = Color.Red,
                    start = Offset(size.width / 2, size.height / 2),
                    end = Offset(size.width / 2, 60f),
                    strokeWidth = 8f
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompassPreview() {
    Compass(135.0f)
}