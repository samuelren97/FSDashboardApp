package com.shunta.fsdashboard.composables.aircraftinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shunta.fsdashboard.composables.generics.Compass
import com.shunta.fsdashboard.composables.generics.Headline1
import com.shunta.fsdashboard.composables.generics.Headline2
import com.shunta.fsdashboard.getDummySimData
import com.shunta.fsdashboard.models.AircraftInfoModel
import com.shunta.fsdashboard.ui.theme.FSDashboardTheme

@Composable
fun AircraftInfo(
    aircraftInfoModel: AircraftInfoModel
) {
    Column(
        modifier = Modifier.padding(top = 20.dp, end = 16.dp, bottom = 16.dp, start = 16.dp)
    ) {
        Headline1("Altitude and bearings")
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Headline2("Altitude")
            Row {
                Text(aircraftInfoModel.alt.toString())
                Text("ft")
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))

            Row {
                val progress: Float = aircraftInfoModel.alt.toFloat() / 40000
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.weight(8F)
                        .align(Alignment.CenterVertically),
                    color = Color(0xFF006300)
                )
                Text(
                    "FL400",
                    modifier = Modifier.weight(2F)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(
                modifier = Modifier.width(175.dp)
                    .padding(8.dp),
                color = Color.LightGray,
            )

            Headline2("Heading")
            Compass(aircraftInfoModel.hdg, Modifier.size(175.dp))
            HorizontalDivider(
                modifier = Modifier.width(175.dp)
                    .padding(8.dp),
                color = Color.LightGray,
            )

            Headline2("Altimeter")
            Text(
                aircraftInfoModel.bar.toString(),
                style = MaterialTheme.typography.displaySmall
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))

        Headline1("Speeds")
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Speed(
            "Indicated Air Speed",
            aircraftInfoModel.speeds.ias,
            maxSpeed = 250F
        )
        Speed(
            "True Air Speed",
            aircraftInfoModel.speeds.tas,
            maxSpeed = 250F,
            size = 80
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AircraftInfoPreview() {
    FSDashboardTheme {
        Column (Modifier.verticalScroll(rememberScrollState())) {
            AircraftInfo(
                getDummySimData().aircraftInfoModel,
            )
        }
    }
}