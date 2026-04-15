package com.cascarita.app.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM team ORDER BY position ASC")
    fun getAllTeams(): Flow<List<TeamEntity>>

    @Query("SELECT * FROM team ORDER BY position ASC")
    suspend fun getAllTeamsOnce(): List<TeamEntity>

    @Query("SELECT * FROM team WHERE isOnCourt = 1 ORDER BY position ASC LIMIT 2")
    suspend fun getOnCourtTeams(): List<TeamEntity>

    @Query("SELECT * FROM team WHERE isOnCourt = 0 ORDER BY position ASC")
    fun getQueuedTeams(): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: TeamEntity): Long

    @Update
    suspend fun updateTeam(team: TeamEntity)

    @Delete
    suspend fun deleteTeam(team: TeamEntity)

    @Query("DELETE FROM team")
    suspend fun deleteAllTeams()

    @Query("UPDATE team SET score = 0")
    suspend fun resetAllScores()

    @Query("UPDATE team SET score = score + 1 WHERE id = :teamId")
    suspend fun incrementScore(teamId: Long)

    @Query("SELECT COUNT(*) FROM team")
    suspend fun getTeamCount(): Int
}
