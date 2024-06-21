package com.namkuzo.feriaspot.data.source

import android.content.Context
import com.namkuzo.feriaspot.data.Zones
import com.namkuzo.feriaspot.di.IoDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

private const val ZONES_FILE_NAME = "zones.json"

class CountryRegionProvider @Inject constructor(
    private val context: Context,
    private val moshi: Moshi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getZones() : Zones? {
        val jsonResult = readJsonFromAssets(ZONES_FILE_NAME).orEmpty()
        val type = Types.newParameterizedType(Zones::class.java)
        val adapter = moshi.adapter<Zones>(type)
        val zones = adapter.fromJson(jsonResult)
        return zones
    }

    private suspend fun readJsonFromAssets(fileName: String): String? = withContext(ioDispatcher){
        return@withContext try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}