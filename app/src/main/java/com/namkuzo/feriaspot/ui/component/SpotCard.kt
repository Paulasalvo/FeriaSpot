package com.namkuzo.feriaspot.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.namkuzo.feriaspot.R
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.data.getListFakeSpot
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@Composable
fun SpotCard(
    spot: Spot,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 32.dp),
        onClick = onClick
    ) {
        Column(
        ) {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.image_not_found),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = spot.comuna
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = spot.standDay
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = spot.schedule
                )
            }
        }
    }
}

@Preview
@Composable
fun SpotCardPreview() {
    FeriaSpotTheme {
        SpotCard(
            spot = getListFakeSpot().first(),
            onClick = {}
        )
    }
}
