package com.cascarita.app.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM game ORDER BY completedAt DESC")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game ORDER BY completedAt DESC LIMIT 10")
    fun getRecentGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameEntity)

    @Query("DELETE FROM game")
    suspend fun deleteAllGames()

    @Query("SELECT COUNT(*) FROM game")
    suspend fun getGameCount(): Int
}
