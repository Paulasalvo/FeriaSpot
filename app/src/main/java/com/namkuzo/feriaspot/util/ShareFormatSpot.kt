package com.namkuzo.feriaspot.util

import com.namkuzo.feriaspot.data.Spot

object ShareFormatSpot {
    fun getShareText(spot: Spot) : String {
        val mapLink = "https://www.google.com/maps/search/?api=1&query=${spot.latlng?.latitude},${spot.latlng?.longitude}"
        val shareText = if (spot.latlng?.latitude != null && spot.latlng.longitude != null) {
            """
            Feria Spot:
            Nombre: ${spot.name}
            Comuna: ${spot.comuna}
            Dias abierta: ${spot.standDay}
            Horario: ${spot.schedule}
            Mapa: $mapLink
        """.trimIndent()
        } else {
            """
            Feria Spot:
            Nombre: ${spot.name}
            Comuna: ${spot.comuna}
            Dias abierta: ${spot.standDay}
            Horario: ${spot.schedule}
        """.trimIndent()
        }
        return shareText
    }
}