package com.namkuzo.feriaspot.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            .padding(vertical = 8.dp, horizontal = 16.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = spot.name
            )
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
