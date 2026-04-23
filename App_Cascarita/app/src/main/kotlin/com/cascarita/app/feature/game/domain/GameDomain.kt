package com.cascarita.app.feature.game.domain

import com.cascarita.app.core.common.GameConstants
import com.cascarita.app.core.common.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class Team(
    val id: Long = 0,
    val name: String,
    val score: Int = 0,
    val position: Int = 0
)

data class GameState(
    val teams: List<Team> = emptyList(),
    val targetScore: Int = 15,
    val isOvertime: Boolean = false,
    val overtimeCount: Int = 0,
    val winner: Team? = null,
    val isGameFinished: Boolean = false
)

interface GameRepository {
    fun observeGameState(): Flow<GameState>
    suspend fun scorePoint(teamId: Long): Result<Unit>
    suspend fun rotateTeams(winnerId: Long): Result<Unit>
    suspend fun resetScores(): Result<Unit>
    suspend fun getOnCourtTeams(): Result<Pair<Team, Team>>
    suspend fun getQueuedTeams(): Result<List<Team>>
    suspend fun checkWinCondition(): Result<Team?>
    suspend fun handleOvertime(): Result<Unit>
    suspend fun updateTargetScore(targetScore: Int): Result<Unit>
}

class ScorePointUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(teamId: Long): Result<Unit> {
        return repository.scorePoint(teamId)
    }
}

class RotateTeamsUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(winnerId: Long): Result<Unit> {
        return repository.rotateTeams(winnerId)
    }
}

class ResetScoresUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.resetScores()
    }
}

class GetOnCourtTeamsUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(): Result<Pair<Team, Team>> {
        return repository.getOnCourtTeams()
    }
}

class GetQueuedTeamsUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(): Result<List<Team>> {
        return repository.getQueuedTeams()
    }
}

class CheckWinConditionUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(): Result<Team?> {
        return repository.checkWinCondition()
    }
}

class HandleOvertimeUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.handleOvertime()
    }
}

class UpdateTargetScoreUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(targetScore: Int): Result<Unit> {
        return repository.updateTargetScore(targetScore)
    }
}

class ObserveGameStateUseCase @Inject constructor(private val repository: GameRepository) {
    operator fun invoke(): Flow<GameState> {
        return repository.observeGameState()
    }
}
