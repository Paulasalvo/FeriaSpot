package com.namkuzo.feriaspot.data

data class FilterCountry(
    val firstItems: List<String> = emptyList(),
    val secondItems: List<String> = emptyList(),
    var firstItemSelected: String = "",
    var secondItemSelected: String = ""
)
