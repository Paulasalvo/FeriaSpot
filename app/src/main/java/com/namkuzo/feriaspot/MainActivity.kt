package com.namkuzo.feriaspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.namkuzo.feriaspot.home.HomeScreen
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeriaSpotTheme {
                Scaffold(
                    topBar = {
                        Row {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "Ferias Libres"
                            )
                        }
                    }
                ) {
                    HomeScreen(
                        modifier = Modifier.padding(it)
                    )
                }

            }
        }
    }
}




