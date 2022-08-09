package com.yb.part5_chapter05.data.repository

import com.yb.part5_chapter05.domain.ArrivalInformation
import com.yb.part5_chapter05.domain.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {
    val stations: Flow<List<Station>>

    suspend fun refreshStations()

    suspend fun getStationArrivals(stationName: String) : List<ArrivalInformation>

    suspend fun updateStation(station: Station)
}