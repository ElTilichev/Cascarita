package com.cascarita.app.feature.game.domain

import com.cascarita.app.core.common.Result

interface GameRepository {
    fun observeGameState(): kotlinx.coroutines.flow.Flow<GameState>
    suspend fun scorePoint(teamId: Long): Result<Unit>
    suspend fun rotateTeams(winnerId: Long): Result<Unit>
    suspend fun resetScores(): Result<Unit>
    suspend fun getOnCourtTeams(): Result<Pair<Team, Team>>
    suspend fun getQueuedTeams(): Result<List<Team>>
    suspend fun checkWinCondition(): Result<Team?>
    suspend fun handleOvertime(): Result<Unit>
}
