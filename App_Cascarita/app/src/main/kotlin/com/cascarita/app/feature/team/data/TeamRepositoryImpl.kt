package com.cascarita.app.feature.team.data

import com.cascarita.app.core.common.Result
import com.cascarita.app.core.database.TeamDao
import com.cascarita.app.core.database.TeamEntity
import com.cascarita.app.feature.team.domain.TeamDetail
import com.cascarita.app.feature.team.domain.TeamListState
import com.cascarita.app.feature.team.domain.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(
    private val teamDao: TeamDao
) : TeamRepository {

    override fun observeTeamList(): Flow<TeamListState> {
        return teamDao.getAllTeams().map { teamEntities ->
            val teams = teamEntities.map { it.toDomain() }
            val onCourtTeams = teams.filter { it.position < 2 }
            val queuedTeams = teams.filter { it.position >= 2 }

            TeamListState(
                onCourtTeams = onCourtTeams,
                queuedTeams = queuedTeams,
                totalWaiting = queuedTeams.size
            )
        }
    }

    override suspend fun addTeam(name: String, captain: String?): Result<Unit> {
        return try {
            val currentCount = teamDao.getTeamCount()
            val newTeam = TeamEntity(
                name = name,
                position = currentCount,
                captain = captain,
                isOnCourt = currentCount < 2
            )
            teamDao.insertTeam(newTeam)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun removeTeam(teamId: Long): Result<Unit> {
        return try {
            val allTeams = teamDao.getAllTeamsOnce().toMutableList()
            val teamToRemove = allTeams.find { it.id == teamId }
            
            if (teamToRemove != null) {
                allTeams.remove(teamToRemove)
                
                // Reposition remaining teams
                allTeams.forEachIndexed { index, team ->
                    teamDao.updateTeam(team.copy(position = index))
                }
                
                teamDao.deleteTeam(teamToRemove)
            }
            
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateTeam(team: TeamDetail): Result<Unit> {
        return try {
            val entity = team.toEntity()
            teamDao.updateTeam(entity)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun TeamEntity.toDomain(): TeamDetail {
        return TeamDetail(
            id = id,
            name = name,
            captain = captain,
            position = position,
            isOnCourt = position < 2,
            isComplete = true // TODO: Add logic to check team completeness
        )
    }

    private fun TeamDetail.toEntity(): TeamEntity {
        return TeamEntity(
            id = id,
            name = name,
            captain = captain,
            position = position,
            isOnCourt = isOnCourt
        )
    }
}
