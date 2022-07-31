package com.yb.part5_chapter05.data.db.entity

import androidx.room.*

data class StationWithSubwaysEntity(
    @Embedded val station : StationEntity,
    @Relation(
        parentColumn = "stationName",
        entityColumn = "subwayId",
        associateBy = Junction(StationSubwayCrossRefEntity::class)
    )
    val subways: List<SubwayEntity>
)
