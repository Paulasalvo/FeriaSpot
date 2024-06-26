package com.namkuzo.feriaspot.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.namkuzo.feriaspot.data.source.Source
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@Composable
fun SpotFilterDialog(
    title: String,
    items: List<String>,
    itemSelected: String,
    onClick: (String) -> Unit,
    onDismissRequest: ()->Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier.padding(vertical = 64.dp, horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = title
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    itemsIndexed(items) {index, item ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onClick(item)
                                }
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                            ,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = itemSelected == item, onClick = {
                                onClick(item)
                            })
                            Text(text = item)
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun SpotFilterDialogPreview() {
    FeriaSpotTheme {
        SpotFilterDialog(
            title = "Selecione",
            items = Source.getAllComunas(),
            itemSelected = "",
            onClick = {},
            onDismissRequest = {}
        )
    }
}
