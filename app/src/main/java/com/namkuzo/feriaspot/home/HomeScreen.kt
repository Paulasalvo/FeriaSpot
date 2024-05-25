package com.namkuzo.feriaspot.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.namkuzo.feriaspot.R
import com.namkuzo.feriaspot.data.LatLng
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.data.getListFakeSpot
import com.namkuzo.feriaspot.ui.component.SpotCard
import com.namkuzo.feriaspot.ui.component.SpotFilterDialog
import com.namkuzo.feriaspot.ui.component.SpotTopBar
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onClickNavigationItem: () -> Unit,
    onClickShare: (Spot) -> Unit,
    onClickMap: (LatLng) -> Unit
) {
    val spotUiState by viewModel.spotsStateFlow.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var dialogFilterState by rememberSaveable{ mutableStateOf(false) }
    var isFilter by rememberSaveable { mutableStateOf(false) }
    var selectedIndexItem by remember { mutableStateOf(-1) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SpotTopBar(
                scrollBehavior = scrollBehavior,
                isFilter = isFilter,
                onClickProfile = {},
                onClickFilter = {
                    dialogFilterState = true
                }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .heightIn(max = 64.dp)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = onClickNavigationItem,
                    icon = {
                        Icon(
                            painter = if (true) painterResource(id = R.drawable.ic_home_on) else painterResource(id = R.drawable.ic_home_off),
                            contentDescription = stringResource(id = R.string.home)
                        )
                    },
                    label = {
                        Text(text = stringResource(id = R.string.home))
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f)
                    )
                )
            }
        }
    ) { paddingValues ->

        when (spotUiState) {
            is SpotUiState.Loading -> {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Loading...")
                }
            }
            is SpotUiState.Success -> {
                HomeScreen(
                    modifier = Modifier.padding(paddingValues),
                    spots = (spotUiState as? SpotUiState.Success)?.spots ?: emptyList(),
                    onClickShare = onClickShare,
                    onClickMap = onClickMap
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

    if (dialogFilterState) {
        SpotFilterDialog(
            items = viewModel.getComunas(),
            itemSelected = selectedIndexItem,
            onClick = { index ->
                selectedIndexItem = index
                viewModel.fetchSpots(index)
                isFilter = index != -1
                dialogFilterState = false
            },
            onDismissRequest = {
                dialogFilterState = false
            }
        )
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    spots: List<Spot>,
    onClickShare: (Spot) -> Unit,
    onClickMap: (LatLng) -> Unit
){
    if (spots.isNotEmpty()) {
        Column(
            modifier = modifier
        ) {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp)
            ) {
                items(spots) { spot ->
                    SpotCard(
                        spot = spot,
                        onClick = {  },
                        onClickShare = onClickShare,
                        onClickMap = onClickMap
                    )
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

@Preview
@Composable
fun HomeScreenPreview() {
    FeriaSpotTheme {
        HomeScreen(
            spots = getListFakeSpot(),
            onClickShare = {},
            onClickMap = {}
        )
    }
}