package com.tafi.footballspin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tafi.footballspin.data.db.converter.ListTeamConverter
import com.tafi.footballspin.data.db.converter.PlayerConverter
import com.tafi.footballspin.data.db.converter.StatisticConverter
import com.tafi.footballspin.data.db.converter.TeamConverter
import com.tafi.footballspin.data.db.dao.MatchDao
import com.tafi.footballspin.data.db.dao.PlayerDao
import com.tafi.footballspin.data.db.dao.StatisticDao
import com.tafi.footballspin.data.db.dao.TeamDao
import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Statistic
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.utils.AppConstants

/**
 * Created by taind-201 on 3/15/2020.
 */
@Database(entities = [Match::class, Player::class, Statistic::class, Team::class],
    version = AppConstants.DB_VERSION,
    exportSchema = false)
@TypeConverters(
    ListTeamConverter::class,
    PlayerConverter::class,
    StatisticConverter::class,
    TeamConverter::class
)
abstract class BaseDb : RoomDatabase() {

    abstract fun getPlayerDao(): PlayerDao

    abstract fun getTeamDao(): TeamDao

    abstract fun getStatisticDao(): StatisticDao

    abstract fun getMatchDao(): MatchDao

}