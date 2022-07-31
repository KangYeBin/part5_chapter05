package com.yb.part5_chapter05.data.db

import androidx.room.*
import com.yb.part5_chapter05.data.db.entity.StationEntity
import com.yb.part5_chapter05.data.db.entity.StationSubwayCrossRefEntity
import com.yb.part5_chapter05.data.db.entity.StationWithSubwaysEntity
import com.yb.part5_chapter05.data.db.entity.SubwayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {

    @Transaction
    @Query("SELECT * FROM StationEntity")
    fun getStationWithSubways(): Flow<List<StationWithSubwaysEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<StationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubways(subways: List<SubwayEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(references: List<StationSubwayCrossRefEntity>)
}