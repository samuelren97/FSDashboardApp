package com.shunta.fsdashboard.composables.generics

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Headline1(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    HorizontalDivider(
        color = Color.Gray,
        thickness = 2.dp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun Headline2(text: String) {
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = text,
        style = MaterialTheme.typography.headlineSmall,
    )
}