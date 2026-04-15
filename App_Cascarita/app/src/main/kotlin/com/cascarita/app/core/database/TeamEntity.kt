package com.cascarita.app.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val score: Int = 0,
    val position: Int = 0,
    val captain: String? = null,
    val isActive: Boolean = false,
    val isOnCourt: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
