package com.namkuzo.feriaspot

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.feature.home.HomeScreen
import com.namkuzo.feriaspot.feature.spotdetail.SpotDetailScreen
import com.namkuzo.feriaspot.util.parcelableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun FeriaSpotApp(
    onClickShare: (Spot) -> Unit,
    onClickMap: (Double, Double) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home>{
            HomeScreen(
                onClickNavigationItem = {},
                onClickSpot = { spot ->
                    navController.navigate(SpotDetail(spot))
                },
                onClickShare = onClickShare,
                onClickMap = onClickMap
            )
        }
        composable<SpotDetail>(
            typeMap = mapOf(typeOf<Spot>() to parcelableType<Spot>())
        ) {
            val spot = it.toRoute<SpotDetail>().spot
            SpotDetailScreen(
                spot = spot,
                onBack = {
                    navController.navigate(Home)
                }
            )
        }
    }
}

@Serializable
object Home

@Serializable
data class SpotDetail(
    val spot: Spot
)
