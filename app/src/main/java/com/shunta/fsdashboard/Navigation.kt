package com.shunta.fsdashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.shunta.fsdashboard.composables.aircraftinfo.AircraftInfo
import com.shunta.fsdashboard.composables.dialogs.ErrorMessageDialog
import com.shunta.fsdashboard.composables.dialogs.TextInputDialog
import com.shunta.fsdashboard.composables.radio.Radios
import com.shunta.fsdashboard.models.SimData
import com.shunta.fsdashboard.networking.WebSocketManager
import kotlinx.coroutines.launch

const val ROUTE_RADIO_SCREEN = "radioScreen"
const val ROUTE_CONNECTION_ERROR_DIALOG = "connectionErrorDialog"
const val ROUTE_AIRCRAFT_INFO_SCREEN = "aircraftInfoScreen"
const val ROUTE_LOADING_SPINNER = "loadingSpinnerDialog"

// TEMP ROUTE
const val ROUTE_CHANGE_SERVER_ADDRESS = "changeServerAddress"

const val START_ROUTE = ROUTE_AIRCRAFT_INFO_SCREEN

var currentRoute: String = ROUTE_LOADING_SPINNER

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController) {
    var simData by remember { mutableStateOf(getDummySimData()) }
    val coroutineScope = rememberCoroutineScope()

    WebSocketManager.init(
        openHandler = {
            coroutineScope.launch {
                navController.navigate(START_ROUTE)
            }
        },
        messageHandler = { text: String ->
            simData = SimData.fromJSON(text)
        },
        failureHandler = { _ : String ->
            coroutineScope.launch {
                navController.navigate(ROUTE_CONNECTION_ERROR_DIALOG)
            }
        }
    )
    WebSocketManager.connectWebSocket(wsServerAddress)

    NavHost(navController, startDestination = ROUTE_LOADING_SPINNER) {
        composable(ROUTE_RADIO_SCREEN) {
            currentRoute = ROUTE_RADIO_SCREEN
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                Radios(
                    simData.frequencies,
                )
            }
        }

        composable(ROUTE_AIRCRAFT_INFO_SCREEN) {
            currentRoute = ROUTE_AIRCRAFT_INFO_SCREEN
            Column (modifier = modifier.verticalScroll(rememberScrollState())) {
                AircraftInfo(
                    simData.aircraftInfoModel,
                )
            }
        }

        dialog(ROUTE_LOADING_SPINNER) {
            currentRoute = ROUTE_LOADING_SPINNER
            CircularProgressIndicator(
                modifier = modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        dialog(ROUTE_CONNECTION_ERROR_DIALOG) {
            currentRoute = ROUTE_CONNECTION_ERROR_DIALOG
            val onDismiss: () -> Unit = {
                WebSocketManager.connectWebSocket(wsServerAddress)
                navController.popBackStack()
            }

            ErrorMessageDialog(
                "An error occurred while trying to connect to the server",
                onDismiss,
                modifier
            )
        }

        dialog(ROUTE_CHANGE_SERVER_ADDRESS) {
            currentRoute = ROUTE_CHANGE_SERVER_ADDRESS
            TextInputDialog(
                "Enter the new server address",
                onButtonClick = { text ->
                    wsServerAddress = text
                    WebSocketManager.connectWebSocket(wsServerAddress)
                },
                onDismiss = { navController.popBackStack() }
            )
        }
    }
}