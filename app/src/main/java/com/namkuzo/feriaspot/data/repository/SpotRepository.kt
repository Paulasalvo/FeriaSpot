package com.namkuzo.feriaspot.data.repository

import com.namkuzo.feriaspot.data.Spot

interface SpotRepository {
    suspend fun getAllSpots(indexComuna: Int) : List<Spot>
}