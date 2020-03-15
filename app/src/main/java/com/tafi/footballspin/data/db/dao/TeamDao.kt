package com.tafi.footballspin.data.db.dao

import androidx.room.*
import com.tafi.footballspin.data.db.model.Team

/**
 * Created by taind-201 on 3/15/2020.
 */
@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: Team): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertListTeam(objects: List<Team>)

    @Update
    fun update(team: Team)

    @Query("SELECT COUNT(id) FROM team")
    fun count(): Long

    @Query("SELECT * FROM team")
    fun loadAll(): List<Team>


}