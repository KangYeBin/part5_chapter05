package com.yb.part5_chapter05.domain

data class Station(
    val stationName: String,
    val isFavorited: Boolean,
    val connectedSubways: List<Subway>
)
