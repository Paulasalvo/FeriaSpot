package com.namkuzo.feriaspot.navigation

import com.namkuzo.feriaspot.data.Spot
import kotlinx.serialization.Serializable

sealed interface NavDestination {
    @Serializable
    object Home

    @Serializable
    data class SpotDetail(
        val spot: Spot
    )
}
