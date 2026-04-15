package com.cascarita.app.feature.game.domain

data class Team(
    val id: Long = 0,
    val name: String,
    val score: Int = 0,
    val position: Int = 0
)

data class GameState(
    val teams: List<Team> = emptyList(),
    val targetScore: Int = 7,
    val isOvertime: Boolean = false,
    val overtimeCount: Int = 0,
    val winner: Team? = null,
    val isGameFinished: Boolean = false
)
