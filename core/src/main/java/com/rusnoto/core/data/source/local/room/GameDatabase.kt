package com.rusnoto.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rusnoto.core.data.source.local.entity.GameDetailEntity
import com.rusnoto.core.data.source.local.entity.NewReleaseEntity

@Database(entities = [NewReleaseEntity::class, GameDetailEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}