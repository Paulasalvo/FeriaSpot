package com.namkuzo.feriaspot

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.ui.theme.FeriaSpotTheme
import com.namkuzo.feriaspot.util.ShareFormatSpot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeriaSpotTheme {
                FeriaSpotApp(
                    onClickShare = ::shareSpot,
                    onClickMap = ::openMap
                )
            }
        }
    }

    private fun shareSpot(spot: Spot) {
        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, ShareFormatSpot.getShareText(spot))
        sendIntent.setType("text/plain")
        startActivity(sendIntent)
    }

    private fun openMap(latitude: Double, longitude: Double) {
        val uri = "http://maps.google.com/maps?q=loc:${latitude},${longitude}?z=15"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.app_not_found), Toast.LENGTH_SHORT).show()
        }
    }
}
