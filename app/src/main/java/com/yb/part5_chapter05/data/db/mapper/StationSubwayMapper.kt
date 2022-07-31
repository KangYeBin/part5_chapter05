package com.yb.part5_chapter05.data.db.mapper

import com.yb.part5_chapter05.data.db.entity.StationWithSubwaysEntity
import com.yb.part5_chapter05.data.db.entity.SubwayEntity
import com.yb.part5_chapter05.domain.Station
import com.yb.part5_chapter05.domain.Subway

fun StationWithSubwaysEntity.toStation() =
    Station(
        stationName = station.stationName,
        isFavorited = station.isFavorited,
        connectedSubways = subways.toSubways()
    )

fun List<StationWithSubwaysEntity>.toStations() = map {
    it.toStation()
}

fun List<SubwayEntity>.toSubways(): List<Subway> = map { Subway.findById(it.subwayId) }
