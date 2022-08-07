package com.yb.part5_chapter05.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Station(
    val stationName: String,
    val isFavorited: Boolean,
    val connectedSubways: List<Subway>
) : Parcelable
