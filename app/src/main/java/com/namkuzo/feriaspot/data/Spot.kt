package com.namkuzo.feriaspot.data

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Immutable
data class Spot(
    val id: Int,
    val name: String,
    val comuna: String,
    val standDay : String,
    val schedule : String,
    val imageUrlEncoded: String,
    val mainStreet: String,
    val stall : String,
    val number : Int,
    val latitude: Double?,
    val longitude: Double?
) : Parcelable
