package com.cascarita.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TeamEntity::class, GameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CascaritaDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun gameDao(): GameDao
}
