package com.tafi.footballspin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tafi.footballspin.data.db.model.Match

/**
 * Created by taind-201 on 3/15/2020.
 */
@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(match: Match): Long

    @Query("SELECT * FROM match")
    fun loadAll(): List<Match>

}