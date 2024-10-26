package com.shunta.fsdashboard.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shunta.fsdashboard.R

@Composable
fun NavItem(
    painter: Painter,
    contentDescription: String,
    labelText: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        label = {
            Row (verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painter,
                    contentDescription = contentDescription,
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                Text(text = labelText)
            }
        },
        selected = selected,
        onClick = onClick
    )
}