package com.shunta.fsdashboard.composables.autopilot

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shunta.fsdashboard.composables.generics.Headline1
import com.shunta.fsdashboard.models.AutopilotModel
import com.shunta.fsdashboard.ui.theme.FSDashboardTheme

@Composable
fun Autopilot(
    autopilotModel: AutopilotModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(top = 20.dp, end = 16.dp, bottom = 16.dp, start = 16.dp),
    ) {
        Headline1("Autopilot")

        Row {
            var baseModifier: Modifier = Modifier.weight(1f)

            var navModifier: Modifier = baseModifier.clickable {
                Log.d("AUTOPILOT", "nav click")
            }
            var hdgModifier: Modifier = baseModifier.clickable {
                Log.d("AUTOPILOT", "hdg click")
            }
            var masterModifier: Modifier = baseModifier.clickable {
                Log.d("AUTOPILOT", "master ap click")
            }
            var flcModifier: Modifier = baseModifier.clickable {
                Log.d("AUTOPILOT", "flc click")
            }

            if (autopilotModel.navState) {
                navModifier = navModifier.border(3.dp, Color(0xFF00AA00))
            }

            if (autopilotModel.hdgState) {
                hdgModifier = hdgModifier.border(3.dp, Color(0xFF00AA00))
            }

            if (autopilotModel.masterState) {
                masterModifier = masterModifier.border(3.dp, Color(0xFF00AA00))
            }

            if (autopilotModel.flcState) {
                flcModifier = flcModifier.border(3.dp, Color(0xFF00AA00))
            }

            Box(
                modifier = modifier.weight(1f).padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "NAV",
                    navModifier.padding(20.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Box(
                modifier = modifier.weight(1f).padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "HDG",
                    hdgModifier.padding(20.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Box(
                modifier = modifier.weight(1f).padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "AP",
                    masterModifier.padding(20.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Box(
                modifier = modifier.weight(1f).padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "FLC",
                    flcModifier.padding(20.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AutopilotPreview() {
    FSDashboardTheme {
        Autopilot(
            autopilotModel = AutopilotModel(
                hdg = 0f,
                alt = 0,
                masterState = true,
                hdgState = false,
                navState = false,
                flcState = false,
            )
        )
    }
}