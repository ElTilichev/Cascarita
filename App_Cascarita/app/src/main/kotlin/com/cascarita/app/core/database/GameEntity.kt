package com.cascarita.app.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val team1Id: Long,
    val team1Name: String,
    val team1Score: Int,
    val team2Id: Long,
    val team2Name: String,
    val team2Score: Int,
    val winnerId: Long,
    val winnerName: String,
    val targetScore: Int,
    val wasOvertime: Boolean,
    val completedAt: Long = System.currentTimeMillis()
)
