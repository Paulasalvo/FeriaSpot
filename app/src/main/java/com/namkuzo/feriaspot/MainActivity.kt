package com.namkuzo.feriaspot

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.namkuzo.feriaspot.data.LatLng
import com.namkuzo.feriaspot.home.HomeScreen
import com.namkuzo.feriaspot.ui.component.SpotTopBar
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeriaSpotTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        SpotTopBar(
                            scrollBehavior = scrollBehavior,
                            onClickProfile = {},
                            onClickFilter = {}
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            NavigationBarItem(
                                selected = true,
                                onClick = { /*TODO*/ },
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
                ) {
                    HomeScreen(
                        modifier = Modifier.padding(it),
                        onClickMap = {
                            openMap(it)
                        }
                    )
                }

            }
        }
    }

    private fun openMap(latLng: LatLng) {
        val uri = "http://maps.google.com/maps?q=loc:${latLng.latitude},${latLng.longitude}?z=15"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.app_not_found), Toast.LENGTH_SHORT).show()
        }
    }
}




