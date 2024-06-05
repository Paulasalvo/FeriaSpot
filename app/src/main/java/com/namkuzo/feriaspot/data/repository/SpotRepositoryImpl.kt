package com.namkuzo.feriaspot.data.repository

import com.namkuzo.feriaspot.data.Spot
import com.namkuzo.feriaspot.data.network.SpotService
import com.namkuzo.feriaspot.data.source.Source
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class SpotRepositoryImpl @Inject constructor(
    private val spotService: SpotService
): SpotRepository {
    override suspend fun getAllSpots(indexComuna: Int): List<Spot> {
        val response = spotService.getAllSpots(comuna = Source.getAllComunas().getOrNull(indexComuna))
        return response.map { dto ->
            Spot(
                id = dto.id,
                name = dto.name,
                comuna = dto.comuna,
                standDay = dto.standDay,
                schedule = dto.schedule,
                imageUrlEncoded = URLEncoder.encode(dto.imageUrl.orEmpty(), StandardCharsets.UTF_8.toString()),
                mainStreet = dto.mainStreet,
                stall = dto.stall,
                number = dto.number,
                latitude = dto.latitud,
                longitude = dto.longitud
            )
        }
    }
}
