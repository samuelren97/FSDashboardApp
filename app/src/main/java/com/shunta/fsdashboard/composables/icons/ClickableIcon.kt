package com.shunta.fsdashboard.composables.icons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shunta.fsdashboard.R

@Composable
fun ClickableIcon(
    iconPainter: Painter,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Column (modifier = Modifier.padding(4.dp)
        .border(
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(8.dp)
        )
        .clickable(onClick = onClick)
        .padding(8.dp)
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ClickableIconPreview() {
    ClickableIcon(
        iconPainter = painterResource(R.drawable.baseline_airplanemode_active_24),
        contentDescription = "send",
        onClick = {}
    )
}