package com.namkuzo.feriaspot.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.namkuzo.feriaspot.R
import com.namkuzo.feriaspot.data.FilterCountry
import com.namkuzo.feriaspot.ui.component.OutlineBox
import com.namkuzo.feriaspot.ui.component.PopupBox
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@Composable
fun SpotFilter(
    filterCountry: FilterCountry,
    onClose: () -> Unit,
    onSelect: (FilterCountry) ->Unit,
    onFilter: () -> Unit,
    onClear: () -> Unit
) {

    var dialogRegionFilterState by rememberSaveable{ mutableStateOf(false) }
    var dialogComunaFilterState by rememberSaveable{ mutableStateOf(false) }

    PopupBox(
        onClickOutside = onClose
    ) {
        Column {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.select_zone),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onClose) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                }
            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                        text = stringResource(id = R.string.region)
                    )
                    OutlineBox(
                        onClick = {
                            dialogRegionFilterState = true
                        },
                        borderWidth = 1.dp,
                        cornerShapeSize = 8.dp
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = filterCountry.firstItemSelected.ifBlank { stringResource(id = R.string.select_region) },
                            color = if (filterCountry.firstItemSelected.isBlank()) Color.Gray else Color.Unspecified
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 32.dp),
                        text = stringResource(id = R.string.comuna)
                    )
                    OutlineBox(
                        onClick = {
                            dialogComunaFilterState = true
                        },
                        borderWidth = 1.dp,
                        cornerShapeSize = 8.dp
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            text = filterCountry.secondItemSelected.ifBlank { stringResource(id = R.string.select_comuna) },
                            color = if (filterCountry.secondItemSelected.isBlank()) Color.Gray else Color.Unspecified
                        )
                    }
                    Column(
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onFilter() },
                            enabled = filterCountry.secondItemSelected.isNotBlank()
                        ) {
                            Text(text = stringResource(id = R.string.apply))
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onClear() }
                        ) {
                            Text(text = stringResource(id = R.string.clear))
                        }
                    }
                }
            }
        }
    }

    if (dialogRegionFilterState) {
        SpotFilterDialog(
            title = stringResource(id = R.string.select_region),
            items = filterCountry.firstItems,
            itemSelected = filterCountry.firstItemSelected,
            onClick = { item ->
                onSelect(filterCountry.copy(firstItemSelected = item))
                dialogRegionFilterState = false
            },
            onDismissRequest = {
                dialogRegionFilterState = false
            }
        )
    }

    if (dialogComunaFilterState) {
        SpotFilterDialog(
            title = stringResource(id = R.string.select_comuna),
            items = filterCountry.secondItems,
            itemSelected = filterCountry.secondItemSelected,
            onClick = { secondItem ->
                onSelect(filterCountry.copy(secondItemSelected = secondItem))
                dialogComunaFilterState = false
            },
            onDismissRequest = {
                dialogComunaFilterState = false
            }
        )
    }
}

@Preview
@Composable
fun SpotFilterPreview() {
    FeriaSpotTheme {
        SpotFilter(
            filterCountry = FilterCountry(),
            onClose = {},
            onSelect = {},
            onFilter = {},
            onClear = {}
        )
    }
}