package com.namkuzo.feriaspot.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.namkuzo.feriaspot.R
import com.namkuzo.feriaspot.data.LatLng
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.data.getListFakeSpot
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@Composable
fun SpotCard(
    spot: Spot,
    onClick: () -> Unit,
    onClickMap: (LatLng) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 32.dp),
        onClick = onClick
    ) {
        Column {
            Box(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
            ) {
                if (spot.imageUrl.isBlank()) {
                    Image(
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.no_images),
                        contentDescription = stringResource(id = R.string.no_image),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth(),
                        model = spot.imageUrl,
                        contentDescription = stringResource(id = R.string.image),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = ""
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth(0.8f),
                    shape = RoundedCornerShape(topEnd = 32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Start,
                        text = spot.name.uppercase(),
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .padding(horizontal = 8.dp)
                    .offset(y = (-24).dp),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {
                        spot.latlng?.let { onClickMap(it) }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_map),
                        contentDescription = stringResource(R.string.map),
                        tint = Color.Unspecified
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (24).dp)
                        .padding(vertical = 16.dp)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = spot.comuna
                    )
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = spot.standDay
                    )
                    Text(
                        text = stringResource(id = R.string.schedule, spot.schedule)
                    )
                }
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
            onClick = {},
            onClickMap = {}
        )
    }
}
