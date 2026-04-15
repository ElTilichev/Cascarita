package com.cascarita.app.feature.game.domain

import com.cascarita.app.core.common.GameConstants
import com.cascarita.app.core.common.Result
import javax.inject.Inject

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

class ObserveGameStateUseCase @Inject constructor(private val repository: GameRepository) {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<GameState> {
        return repository.observeGameState()
    }
}
