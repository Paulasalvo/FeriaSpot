package com.namkuzo.feriaspot.data

data class Zones(
    val name: String,
    val regions: List<Region>
)

data class Region(
    val communes: List<Commune>,
    val name: String,
    val number: String,
    val romanNumber: String
)

data class Commune(
    val name: String
)
