package com.cascarita.app.feature.team.domain

import com.cascarita.app.core.common.Result
import javax.inject.Inject

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

class UpdateTeamUseCase @Inject constructor(private val repository: TeamRepository) {
    suspend operator fun invoke(team: TeamDetail): Result<Unit> {
        return repository.updateTeam(team)
    }
}

class ObserveTeamListUseCase @Inject constructor(private val repository: TeamRepository) {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<TeamListState> {
        return repository.observeTeamList()
    }
}
