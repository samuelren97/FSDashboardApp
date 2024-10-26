package com.shunta.fsdashboard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shunta.fsdashboard.composables.AppTopBar
import com.shunta.fsdashboard.composables.NavItem
import com.shunta.fsdashboard.models.AircraftInfoModel
import com.shunta.fsdashboard.models.Frequency
import com.shunta.fsdashboard.models.SimData
import com.shunta.fsdashboard.models.Speeds
import com.shunta.fsdashboard.networking.WebSocketManager
import com.shunta.fsdashboard.ui.theme.FSDashboardTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

var wsServerAddress: String = "ws://10.0.2.2:8765"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController: NavHostController = rememberNavController()
            var selectedRoute by remember { mutableStateOf(ROUTE_AIRCRAFT_INFO_SCREEN) }
            val navigator: Navigator = Navigator(navController)
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            FSDashboardTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            NavItem(
                                painter =
                                    painterResource(R.drawable.baseline_airplanemode_active_24),
                                contentDescription = "airplane",
                                labelText = "Aircraft Information",
                                selected = selectedRoute == ROUTE_AIRCRAFT_INFO_SCREEN,
                            ) {
                                selectedRoute = ROUTE_AIRCRAFT_INFO_SCREEN
                                toggleDrawer(scope, drawerState)
                                navigator.navigateAircraftInfo()
                            }
                            NavItem(
                                painter =
                                    painterResource(R.drawable.baseline_cell_tower_24),
                                contentDescription = "cell tower",
                                labelText = "Radios",
                                selected = selectedRoute == ROUTE_RADIO_SCREEN,
                            ) {
                                selectedRoute = ROUTE_RADIO_SCREEN
                                toggleDrawer(scope, drawerState)
                                navigator.navigateRadio()
                            }
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            AppTopBar(
                                navController,
                                onMenuClick = { toggleDrawer(scope, drawerState) }
                            )
                        }
                    ){ innerPadding ->
                        Navigation(Modifier.padding(innerPadding), navController)
                    }
                }
            }
        }
    }

    private fun toggleDrawer(scope: CoroutineScope, drawerState: DrawerState) {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    override fun onDestroy() {
        WebSocketManager.disconnect()
        super.onDestroy()
    }
}

fun getDummySimData(): SimData {
    return SimData(
        frequencies = List(1)
        { index: Int ->
            Frequency(
                1,
                name = "VHF ${index + 1}",
                active = "Not available",
                standby = "Not available",
            )
        },
        aircraftInfoModel = AircraftInfoModel(
            alt = 30000,
            hdg = 252f,
            speeds = Speeds(
                ias = 150,
                gs = 204,
                tas = 204,
            )
        )
    )
}