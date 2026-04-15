package com.cascarita.app.feature.team.domain

import com.cascarita.app.core.common.Result
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    fun observeTeamList(): Flow<TeamListState>
    suspend fun addTeam(name: String, captain: String?): Result<Unit>
    suspend fun removeTeam(teamId: Long): Result<Unit>
    suspend fun updateTeam(team: TeamDetail): Result<Unit>
}
