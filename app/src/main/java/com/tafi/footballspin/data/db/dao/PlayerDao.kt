package com.tafi.footballspin.data.db.dao

import androidx.room.*
import com.tafi.footballspin.data.db.model.Player

/**
 * Created by taind-201 on 3/15/2020.
 */
@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: Player): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertListPlayer(objects: List<Player>)

    @Update
    fun update(player: Player)

    @Query("SELECT COUNT(id) FROM player")
    fun count(): Long

    @Query("SELECT * FROM player")
    fun loadAll(): List<Player>

}