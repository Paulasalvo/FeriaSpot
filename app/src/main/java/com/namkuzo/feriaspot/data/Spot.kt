package com.namkuzo.feriaspot.data

data class Spot(
    val id: Int,
    val name: String
)

fun getListFakeSpot() = listOf(
    Spot(1, "Test 1"),
    Spot(2, "Test 2"),
    Spot(3, "Test 3"),
    Spot(4, "Test 4"),
    Spot(5, "Test 5")
)