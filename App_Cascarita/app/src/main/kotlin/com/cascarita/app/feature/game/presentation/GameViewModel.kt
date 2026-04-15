package com.cascarita.app.feature.game.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascarita.app.core.common.GameConstants
import com.cascarita.app.core.common.Result
import com.cascarita.app.feature.game.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GameUiState(
    val team1: Team? = null,
    val team2: Team? = null,
    val queuedTeams: List<Team> = emptyList(),
    val targetScore: Int = GameConstants.DEFAULT_TARGET_SCORE,
    val isOvertime: Boolean = false,
    val overtimeCount: Int = 0,
    val winner: Team? = null,
    val isGameFinished: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class GameViewModel @Inject constructor(
    private val scorePointUseCase: ScorePointUseCase,
    private val rotateTeamsUseCase: RotateTeamsUseCase,
    private val resetScoresUseCase: ResetScoresUseCase,
    private val getOnCourtTeamsUseCase: GetOnCourtTeamsUseCase,
    private val getQueuedTeamsUseCase: GetQueuedTeamsUseCase,
    private val checkWinConditionUseCase: CheckWinConditionUseCase,
    private val handleOvertimeUseCase: HandleOvertimeUseCase,
    private val observeGameStateUseCase: ObserveGameStateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        observeGameState()
    }

    private fun observeGameState() {
        observeGameStateUseCase().onEach { gameState ->
            val teams = gameState.teams
            val team1 = if (teams.size > 0) teams[0] else null
            val team2 = if (teams.size > 1) teams[1] else null
            val queuedTeams = teams.filter { it.position >= 2 }

            // Check for overtime
            val isOvertime = team1 != null && team2 != null &&
                team1.score == gameState.targetScore - 1 &&
                team2.score == gameState.targetScore - 1

            _uiState.value = _uiState.value.copy(
                team1 = team1,
                team2 = team2,
                queuedTeams = queuedTeams,
                targetScore = gameState.targetScore,
                isOvertime = isOvertime,
                winner = gameState.winner,
                isGameFinished = gameState.isGameFinished
            )

            // Check win condition
            checkWinCondition()
        }.launchIn(viewModelScope)
    }

    fun scorePoint(teamId: Long) {
        viewModelScope.launch {
            when (val result = scorePointUseCase(teamId)) {
                is Result.Success -> {
                    // Win condition will be checked automatically by flow
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        error = result.exception.message
                    )
                }
                else -> {}
            }
        }
    }

    private fun checkWinCondition() {
        viewModelScope.launch {
            when (val result = checkWinConditionUseCase()) {
                is Result.Success -> {
                    val winner = result.data
                    if (winner != null) {
                        _uiState.value = _uiState.value.copy(
                            winner = winner,
                            isGameFinished = true
                        )
                        // Auto-rotate after a delay
                        rotateTeams(winner.id)
                    }
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        error = result.exception.message
                    )
                }
                else -> {}
            }
        }
    }

    fun rotateTeams(winnerId: Long) {
        viewModelScope.launch {
            when (val result = rotateTeamsUseCase(winnerId)) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        winner = null,
                        isGameFinished = false,
                        isOvertime = false
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        error = result.exception.message
                    )
                }
                else -> {}
            }
        }
    }

    fun resetScores() {
        viewModelScope.launch {
            when (val result = resetScoresUseCase()) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        winner = null,
                        isGameFinished = false,
                        isOvertime = false
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        error = result.exception.message
                    )
                }
                else -> {}
            }
        }
    }
}
