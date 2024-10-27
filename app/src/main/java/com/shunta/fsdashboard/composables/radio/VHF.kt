package com.shunta.fsdashboard.composables.radio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shunta.fsdashboard.composables.generics.Headline2
import com.shunta.fsdashboard.ui.theme.FSDashboardTheme

@Composable
fun VHF(
    id: Int,
    name: String,
    active: String,
    standby: String,
    onSwap: (Int) -> Unit,
    onSet: (Int, String) -> Unit,
    defaultSetVal: String = "124.000"
) {
    var freqState by remember { mutableStateOf(defaultSetVal) }

    Column (modifier = Modifier.padding(bottom = 20.dp)) {
        Headline2(text = name)
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1.0F),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Active",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = active,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Column(
                modifier = Modifier.weight(1.0F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {onSwap(id)}
                    ) {
                        Icon(
                            Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                            contentDescription = "Swap"
                        )
                        Icon(
                            Icons.AutoMirrored.Sharp.KeyboardArrowRight,
                            contentDescription = "Swap"
                        )
                    }
                }
                OutlinedTextField(
                    modifier = Modifier.padding(top = 10.dp),
                    value = freqState,
                    onValueChange = { text -> freqState = text }
                )

                OutlinedButton(
                    onClick = { onSet(id, freqState) },
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Text(text = "Set")
                }
            }

            Column(
                modifier = Modifier.weight(1.0F),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Standby",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = standby,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VHFPreview() {
    FSDashboardTheme {
        VHF(
            id = 1,
            name = "VHF 1",
            active = "124.000",
            standby = "118.650",
            onSwap = {_ -> },
            onSet = {_, _ -> },
        )
    }
}