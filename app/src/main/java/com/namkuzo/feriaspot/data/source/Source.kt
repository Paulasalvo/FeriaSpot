package com.namkuzo.feriaspot.data.source

import com.namkuzo.feriaspot.data.Spot

object Source {
    fun getAllComunas() = listOf(
        "ALHUÉ",
        "BUIN",
        "CERRILLOS",
        "CERRO NAVIA",
        "COLINA",
        "CONCHALÍ",
        "CURACAVÍ",
        "EL BOSQUE",
        "EL MONTE",
        "ESTACIÓN CENTRAL",
        "HUECHURABA",
        "INDEPENDENCIA",
        "ISLA DE MAIPO",
        "LA CISTERNA",
        "LA FLORIDA",
        "LA GRANJA",
        "LA PINTANA",
        "LA REINA",
        "LAMPA",
        "LAS CONDES",
        "LO BARNECHEA",
        "LO ESPEJO",
        "LO PRADO",
        "MACUL",
        "MAIPU",
        "MARIA PINTO",
        "MELIPILLA",
        "ÑUÑOA",
        "PADRE HURTADO",
        "PAINE",
        "PEDRO AGUIRRE CERDA",
        "PEÑAFLOR",
        "PEÑALOLEN",
        "PROVIDENCIA",
        "PUDAHUEL",
        "PUENTE ALTO",
        "QUILICURA",
        "QUINTA NORMAL",
        "RECOLETA",
        "RENCA",
        "SAN BERNARDO",
        "SAN JOAQUÍN",
        "SAN JOSÉ DE MAIPO",
        "SAN MIGUEL",
        "SAN RAMÓN",
        "SANTIAGO",
        "TALAGANTE",
        "TILTIL",
        "VITACURA"
    )

    fun getAllRegions() = listOf(
        "Arica y Parinacota",
        "Tarapacá",
        "Antofagasta",
        "Atacama",
        "Coquimbo",
        "Valparaíso",
        "Región del Libertador Gral. Bernardo O’Higgins",
        "Región del Maule",
        "Región de Ñuble",
        "Región del Biobío",
        "Región de la Araucanía",
        "Región de Los Ríos",
        "Región de Los Lagos",
        "Región Aisén del Gral. Carlos Ibáñez del Campo",
        "Región de Magallanes y de la Antártica Chilena",
        "Región Metropolitana de Santiago"
    )

    fun getListFakeSpot() = listOf(
        Spot(
            id = 1,
            name = "Test 1",
            comuna = "La Florida",
            standDay = "Lunes - Martes",
            schedule = "08:30 - 14:00",
            imageUrlEncoded = "",
            mainStreet = "Rojas magallanes",
            stall = "puestos 20",
            number = 20,
            latitude = null,
            longitude = null
        ),
        Spot(
            2,
            "Test 2",
            "La Reina",
            "Lunes - Martes",
            "08:30 - 14:00",
            "",
            "Rojas magallanes",
            stall = "puestos 20",
            number = 20,
            latitude = null,
            longitude = null
        ),
        Spot(
            3,
            "Test 3",
            "Las Condes",
            "Lunes - Martes",
            "08:30 - 14:00",
            "",
            "Rojas magallanes",
            stall = "puestos 20",
            number = 20,
            latitude = null,
            longitude = null
        )
    )

    fun getFakeSpot() =
        Spot(
            1,
            "Test 1",
            "La Florida",
            "Lunes - Martes",
            "08:30 - 14:00",
            "",
            "Rojas magallanes",
            "50",
            number = 20,
            latitude = null,
            longitude = null
        )
}

