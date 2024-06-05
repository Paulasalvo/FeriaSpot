package com.namkuzo.feriaspot.data.dto

data class SpotDTO(
    val id : Int = 0,
    val name : String,
    val comuna : String,
    val mainStreet : String,
    val startingStreet : String,
    val endingStreet : String,
    val standDay : String,
    val schedule : String,
    val stall : String,
    val number : Int,
    val longitud : Double?,
    val latitud: Double?,
    val imageUrl: String?,
    val daySpots: List<DaySpotDTO>?
) {
    data class DaySpotDTO(
        val day: String
    )
}
