package com.cascarita.app.feature.team.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascarita.app.core.common.Result
import com.cascarita.app.feature.team.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TeamUiState(
    val onCourtTeams: List<TeamDetail> = emptyList(),
    val queuedTeams: List<TeamDetail> = emptyList(),
    val totalWaiting: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAddTeamDialog: Boolean = false
)

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val addTeamUseCase: AddTeamUseCase,
    private val removeTeamUseCase: RemoveTeamUseCase,
    private val deleteAllTeamsUseCase: DeleteAllTeamsUseCase,
    private val observeTeamListUseCase: ObserveTeamListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TeamUiState())
    val uiState: StateFlow<TeamUiState> = _uiState.asStateFlow()

    init {
        observeTeamList()
    }

    private fun observeTeamList() {
        observeTeamListUseCase().onEach { teamListState ->
            _uiState.value = _uiState.value.copy(
                onCourtTeams = teamListState.onCourtTeams,
                queuedTeams = teamListState.queuedTeams,
                totalWaiting = teamListState.totalWaiting
            )
        }.launchIn(viewModelScope)
    }

    fun showAddTeamDialog() {
        _uiState.value = _uiState.value.copy(showAddTeamDialog = true)
    }

    fun hideAddTeamDialog() {
        _uiState.value = _uiState.value.copy(showAddTeamDialog = false)
    }

    fun addTeam(name: String, captain: String? = null) {
        viewModelScope.launch {
            when (val result = addTeamUseCase(name, captain)) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        showAddTeamDialog = false,
                        error = null
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

    fun removeTeam(teamId: Long) {
        viewModelScope.launch {
            when (val result = removeTeamUseCase(teamId)) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(error = null)
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

    fun deleteAllTeams() {
        viewModelScope.launch {
            when (val result = deleteAllTeamsUseCase()) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(error = null)
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
