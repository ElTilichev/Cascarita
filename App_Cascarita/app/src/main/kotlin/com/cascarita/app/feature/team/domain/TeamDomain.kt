package com.cascarita.app.feature.team.domain

import com.cascarita.app.core.common.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class TeamDetail(
    val id: Long = 0,
    val name: String,
    val captain: String? = null,
    val position: Int = 0,
    val isOnCourt: Boolean = false,
    val isComplete: Boolean = true
)

data class TeamListState(
    val onCourtTeams: List<TeamDetail> = emptyList(),
    val queuedTeams: List<TeamDetail> = emptyList(),
    val totalWaiting: Int = 0
)

interface TeamRepository {
    fun observeTeamList(): Flow<TeamListState>
    suspend fun addTeam(name: String, captain: String?): Result<Unit>
    suspend fun removeTeam(teamId: Long): Result<Unit>
    suspend fun updateTeam(team: TeamDetail): Result<Unit>
    suspend fun deleteAllTeams(): Result<Unit>
}

class AddTeamUseCase @Inject constructor(private val repository: TeamRepository) {
    suspend operator fun invoke(name: String, captain: String?): Result<Unit> {
        if (name.isBlank()) {
            return Result.Error(Exception("Team name cannot be empty"))
        }
        return repository.addTeam(name, captain)
    }
}

class RemoveTeamUseCase @Inject constructor(private val repository: TeamRepository) {
    suspend operator fun invoke(teamId: Long): Result<Unit> {
        return repository.removeTeam(teamId)
    }
}

class DeleteAllTeamsUseCase @Inject constructor(private val repository: TeamRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.deleteAllTeams()
    }
}

class UpdateTeamUseCase @Inject constructor(private val repository: TeamRepository) {
    suspend operator fun invoke(team: TeamDetail): Result<Unit> {
        return repository.updateTeam(team)
    }
}

class ObserveTeamListUseCase @Inject constructor(private val repository: TeamRepository) {
    operator fun invoke(): Flow<TeamListState> {
        return repository.observeTeamList()
    }
}
