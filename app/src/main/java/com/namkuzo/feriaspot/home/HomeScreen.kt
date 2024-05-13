package com.namkuzo.feriaspot.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.ui.component.SpotCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val spotUiState by viewModel.spotsStateFlow.collectAsState()

    when (spotUiState) {
        is SpotUiState.Loading -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Loading...")
            }
        }
        is SpotUiState.Success -> {
            HomeScreen(
                modifier = modifier,
                spots = (spotUiState as? SpotUiState.Success)?.spots ?: emptyList()
            )
        }
        is SpotUiState.Error -> {
            BasicAlertDialog(
                onDismissRequest = { /*TODO*/ }
            ) {
                Card {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Error ${(spotUiState as? SpotUiState.Error)?.message}"
                    )
                }
            }
        }
        else -> {
            // NO_OP
        }
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    spots: List<Spot>
){
    if (spots.isNotEmpty()) {
        Column(
            modifier = modifier
        ) {
            LazyColumn {
                items(spots) { spot ->
                    SpotCard(spot = spot) {

                    }
                }
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No hay nada :(")
        }
    }
}