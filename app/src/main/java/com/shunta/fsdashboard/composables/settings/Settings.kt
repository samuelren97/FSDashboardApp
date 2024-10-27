package com.shunta.fsdashboard.composables.settings

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shunta.fsdashboard.ROUTE_SETTINGS_SCREEN
import com.shunta.fsdashboard.ROUTE_TEXT_INPUT
import com.shunta.fsdashboard.composables.dialogs.TextInputInstance
import com.shunta.fsdashboard.composables.generics.Headline1
import com.shunta.fsdashboard.currentRoute
import com.shunta.fsdashboard.ui.theme.FSDashboardTheme

@Composable
fun Settings(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var webSocketServerAddress by remember { mutableStateOf("ws://10.0.2.2:8765") }

    Column (modifier = modifier.fillMaxWidth()) {
        Headline1("Settings")

        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable {
                    TextInputInstance.message = "Enter websocket server address"
                    TextInputInstance.onOkClick = {text: String ->
                        Log.d("SETTINGS", "New websocket server: $text")
                        navController.popBackStack()
                        currentRoute = ROUTE_SETTINGS_SCREEN
                        webSocketServerAddress = text
                    }
                    navController.navigate(ROUTE_TEXT_INPUT)
                },
            horizontalArrangement = Arrangement.Center
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Websocket server", fontWeight = FontWeight(500))
                Text(
                    webSocketServerAddress,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp)
        Spacer(modifier = Modifier.padding(8.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {}
            ) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    FSDashboardTheme {
        Settings(NavHostController(LocalContext.current))
    }
}