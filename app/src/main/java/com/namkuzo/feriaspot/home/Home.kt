package com.namkuzo.feriaspot.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@Composable
fun Home(){

}

@Composable
fun FeriaCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(15.dp)
    ) {
        Text(text = "FeriaCard")
    }
}



@Preview
@Composable
fun FeriaCardPreview(){
    FeriaSpotTheme {
        FeriaCard()
    }
}
