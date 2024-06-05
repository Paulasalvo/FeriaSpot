package com.namkuzo.feriaspot.data.network

import com.namkuzo.feriaspot.data.dto.SpotDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotService {
    @GET("allSpots")
    suspend fun getAllSpots(
        @Query("comuna") comuna: String? = null
    ) : List<SpotDTO>
}
