package com.shunta.fsdashboard.composables.radio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shunta.fsdashboard.ROUTE_ERROR
import com.shunta.fsdashboard.composables.generics.Headline1
import com.shunta.fsdashboard.getDummySimData
import com.shunta.fsdashboard.models.ErrorInstance
import com.shunta.fsdashboard.models.Frequency
import com.shunta.fsdashboard.networking.ApiClient
import com.shunta.fsdashboard.ui.theme.FSDashboardTheme

@Composable
fun Radios(
    frequencies: List<Frequency>,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.padding(top = 20.dp, end = 16.dp, bottom = 16.dp, start = 16.dp)
    ) {
        Headline1("Radios")

        frequencies.forEach { freq ->
            VHF(
                id = freq.id,
                name = freq.name,
                active = freq.active,
                standby = freq.standby,
                onSwap = { id ->
                    ApiClient.swapFreq(
                        id,
                        ifSuccess = {},
                        ifFail = { t: Throwable ->
                            ErrorInstance.t = t
                            ErrorInstance.message = "An error occurred while swapping frequencies"
                            navController.navigate(ROUTE_ERROR)
                        }
                    )
                },
                onSet = {id: Int, text: String ->
                    ApiClient.setStandbyFreq(
                        id,
                        text,
                        ifSuccess = {},
                        ifFail = { t: Throwable ->
                            ErrorInstance.t = t
                            ErrorInstance.message = "An error occurred on setting standby frequency"
                            navController.navigate(ROUTE_ERROR)
                        }
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadiosPreview() {
    FSDashboardTheme {
        Radios(
            getDummySimData().frequencies,
            NavHostController(LocalContext.current)
        )
    }
}