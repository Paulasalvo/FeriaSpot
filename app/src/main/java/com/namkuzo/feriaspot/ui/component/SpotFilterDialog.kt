package com.namkuzo.feriaspot.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.namkuzo.feriaspot.data.source.Source
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@Composable
fun SpotFilterDialog(
    items: List<String>,
    itemSelected: Int,
    onClick: (Int) -> Unit,
    onDismissRequest: ()->Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(
                    text = "Seleciona una comuna"
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
                                    onClick(index)
                                }
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                            ,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = itemSelected == index, onClick = {
                                onClick(index)
                            })
                            Text(text = item)
                        }
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { onClick(-1) }
                    ) {
                        Text(text = "Limpiar")
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
            items = Source.getAllComunas(),
            itemSelected = 0,
            onClick = {},
            onDismissRequest = {}
        )
    }
}
