package com.shunta.fsdashboard.composables.radio

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shunta.fsdashboard.composables.generics.Headline1
import com.shunta.fsdashboard.getDummySimData
import com.shunta.fsdashboard.models.Frequency
import com.shunta.fsdashboard.ui.theme.FSDashboardTheme

@Composable
fun Radios(
    frequencies: List<Frequency>,
) {
    Column(
        modifier = Modifier.padding(top = 20.dp, end = 16.dp, bottom = 16.dp, start = 16.dp)
    ) {
        Headline1("Radios")

        frequencies.forEach { freq ->
            VHF(
                id = freq.name,
                active = freq.active,
                standby = freq.standby,
                onSwap = { Log.d("VHF", "Swap requested") },
                onSet = { text: String -> Log.d("VHF", text) },
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
        )
    }
}