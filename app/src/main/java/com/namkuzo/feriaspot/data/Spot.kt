package com.namkuzo.feriaspot.data

data class Spot(
    val id: Int,
    val name: String,
    val comuna: String,
    val standDay : String,
    val schedule : String,
    val imageUrl: String,
    val latlng: LatLng?
)

fun getListFakeSpot() = listOf(
    Spot(1, "Test 1","La Florida", "Lunes - Martes", "08:30 - 14:00", "", null),
    Spot(2, "Test 2","La Reina", "Lunes - Martes", "08:30 - 14:00", "", null),
    Spot(3, "Test 3","Las Condes", "Lunes - Martes", "08:30 - 14:00", "", null),
    Spot(4, "Test 4","Buin", "Lunes - Martes", "08:30 - 14:00", "", null),
    Spot(5, "Test 5","La Florida", "Lunes - Martes", "08:30 - 14:00", "", null)
)