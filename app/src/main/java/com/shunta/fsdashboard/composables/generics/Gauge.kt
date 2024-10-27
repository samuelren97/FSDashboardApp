// BASED ON
// https://proandroiddev.com/creating-a-custom-gauge-speedometer-in-jetpack-compose-bd3c3d72074b

package com.shunta.fsdashboard.composables.generics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Gauge(
    inputValue: Float,
    progressColors: List<Color>,
    sizeModifier: Int = 120,
    trackColor: Color = Color(0xFFE0E0E0),
) {

    val meterValue = getMeterValue(inputValue)
    Box(modifier = Modifier.size(sizeModifier.dp)) {
        val isDark = isSystemInDarkTheme()
        Canvas(modifier = Modifier.fillMaxSize()) {
            val sweepAngle = 240f
            val fillSwipeAngle = (meterValue / 100f) * sweepAngle
            val height = size.height
            val width = size.width
            val startAngle = 150f
            val arcHeight = height - 20.dp.toPx()

            var needleColor = Color.Black
            if (isDark) {
                needleColor = Color.Gray
            }

            drawArc(
                color = trackColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset((width - height + 60f) / 2f, (height - arcHeight) / 2f),
                size = Size(arcHeight, arcHeight),
                style = Stroke(width = (sizeModifier * 3f) / 12f, cap = StrokeCap.Round)
            )

            drawArc(
                brush = Brush.horizontalGradient(progressColors),
                startAngle = startAngle,
                sweepAngle = fillSwipeAngle,
                useCenter = false,
                topLeft = Offset((width - height + 60f) / 2f, (height - arcHeight) / 2),
                size = Size(arcHeight, arcHeight),
                style = Stroke(width = (sizeModifier * 3f) / 12f, cap = StrokeCap.Round)
            )
            val centerOffset = Offset(width / 2f, height / 2.09f)
            drawCircle(needleColor, (sizeModifier * 1.8f) / 12f, centerOffset)

            // Calculate needle angle based on inputValue
            val needleAngle = (meterValue / 100f) * sweepAngle + startAngle
            val needleLength = (sizeModifier * 13f) / 12f // Adjust this value to control needle length
            val needleBaseWidth = sizeModifier / 12f // Adjust this value to control the base width


            val needlePath = Path().apply {
                // Calculate the top point of the needle
                val topX = centerOffset.x + needleLength * cos(
                    Math.toRadians(needleAngle.toDouble()).toFloat()
                )
                val topY = centerOffset.y + needleLength * sin(
                    Math.toRadians(needleAngle.toDouble()).toFloat()
                )

                // Calculate the base points of the needle
                val baseLeftX = centerOffset.x + needleBaseWidth * cos(
                    Math.toRadians((needleAngle - 90).toDouble()).toFloat()
                )
                val baseLeftY = centerOffset.y + needleBaseWidth * sin(
                    Math.toRadians((needleAngle - 90).toDouble()).toFloat()
                )
                val baseRightX = centerOffset.x + needleBaseWidth * cos(
                    Math.toRadians((needleAngle + 90).toDouble()).toFloat()
                )
                val baseRightY = centerOffset.y + needleBaseWidth * sin(
                    Math.toRadians((needleAngle + 90).toDouble()).toFloat()
                )

                moveTo(topX, topY)
                lineTo(baseLeftX, baseLeftY)
                lineTo(baseRightX, baseRightY)
                close()
            }

            drawPath(
                color = needleColor,
                path = needlePath
            )
        }
    }
}
private fun getMeterValue(inputPercentage: Float): Int {
    return if (inputPercentage < 0) {
        0
    } else if (inputPercentage > 1F) {
        100
    } else {
        (inputPercentage * 100).toInt()
    }
}