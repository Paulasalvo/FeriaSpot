package com.namkuzo.feriaspot.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.namkuzo.feriaspot.R
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.data.source.Source
import com.namkuzo.feriaspot.ui.component.SpotCard
import com.namkuzo.feriaspot.ui.component.SpotTopBar
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickNavigationItem: () -> Unit,
    onClickSpot: (Spot) -> Unit,
    onClickShare: (Spot) -> Unit,
    onClickMap: (Double, Double) -> Unit
) {
    val spotUiState by viewModel.spotsStateFlow.collectAsStateWithLifecycle()
    val filterState by viewModel.filterCountry.collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var dialogFilterState by rememberSaveable{ mutableStateOf(false) }
    var isFilter by rememberSaveable { mutableStateOf(false) }

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

        when (val state = spotUiState) {
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
                    spots = state.spots,
                    onClickSpot = onClickSpot,
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
                            text = "Error ${state.message}"
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
        SpotFilter(
            filterCountry = filterState,
            onClose = {
                dialogFilterState = false
            },
            onSelect = viewModel::setFilterSelected,
            onFilter = {
                viewModel.applyFilter()
                dialogFilterState = false
                isFilter = true
            },
            onClear ={
                viewModel.clearFilter()
                dialogFilterState = false
                isFilter = false
            }
        )
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    spots: List<Spot>,
    onClickSpot: (Spot) -> Unit,
    onClickShare: (Spot) -> Unit,
    onClickMap: (Double, Double) -> Unit
){
    if (spots.isNotEmpty()) {
        Column(
            modifier = modifier
        ) {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp)
            ) {
                items(
                    items = spots,
                    key = { spot ->
                        spot.id
                    }
                ) { spot ->
                    SpotCard(
                        spot = spot,
                        onClick = {
                            onClickSpot(it)
                        },
                        onClickShare = onClickShare,
                        onClickMap = onClickMap
                    )
                }
            }
        }
    } else {
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.no_result_found),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    FeriaSpotTheme {
        HomeScreen(
            spots = Source.getListFakeSpot(),
            onClickSpot = {},
            onClickShare = {},
            onClickMap = { _, _ -> }
        )
    }
}

@Preview
@Composable
fun HomeScreenEmptyPreview() {
    FeriaSpotTheme {
        HomeScreen(
            spots = emptyList(),
            onClickSpot = {},
            onClickShare = {},
            onClickMap = { _, _ -> }
        )
    }
}