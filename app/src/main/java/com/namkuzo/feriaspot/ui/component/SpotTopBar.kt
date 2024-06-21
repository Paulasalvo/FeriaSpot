package com.namkuzo.feriaspot.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.namkuzo.feriaspot.R
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotTopBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    isFilter: Boolean,
    onClickProfile: () -> Unit,
    onClickFilter: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                scrolledContainerColor = MaterialTheme.colorScheme.background
            ),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.ferias_libres),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                enabled = false,
                onClick = onClickProfile
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_profile_default),
                    contentDescription = stringResource(id = R.string.icon_profile),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                onClick = onClickFilter
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = if(isFilter) painterResource(id = R.drawable.ic_filter_turn_on) else painterResource(id = R.drawable.ic_filter_turn_off),
                    contentDescription = stringResource(id = R.string.icon_filter),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SpotTopBarPreview() {
    FeriaSpotTheme {
        SpotTopBar(
            isFilter = true,
            onClickProfile = {},
            onClickFilter = {}
        )
    }
}