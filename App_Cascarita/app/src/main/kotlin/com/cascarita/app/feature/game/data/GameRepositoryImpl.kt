package com.cascarita.app.feature.game.data

import com.cascarita.app.core.common.GameConstants
import com.cascarita.app.core.common.Result
import com.cascarita.app.core.database.GameDao
import com.cascarita.app.core.database.GameEntity
import com.cascarita.app.core.database.TeamDao
import com.cascarita.app.core.database.TeamEntity
import com.cascarita.app.feature.game.domain.GameRepository
import com.cascarita.app.feature.game.domain.GameState
import com.cascarita.app.feature.game.domain.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl @Inject constructor(
    private val teamDao: TeamDao,
    private val gameDao: GameDao
) : GameRepository {

    override fun observeGameState(): Flow<GameState> {
        return teamDao.getAllTeams().map { teamEntities ->
            val teams = teamEntities.map { it.toDomain() }
            val onCourtTeams = teams.filter { it.position < 2 }
            val targetScore = GameConstants.DEFAULT_TARGET_SCORE
            
            // Check for overtime condition
            val isOvertime = onCourtTeams.size == 2 && 
                onCourtTeams[0].score == targetScore - 1 && 
                onCourtTeams[1].score == targetScore - 1
            
            GameState(
                teams = teams,
                targetScore = targetScore,
                isOvertime = isOvertime
            )
        }
    }

    override suspend fun scorePoint(teamId: Long): Result<Unit> {
        return try {
            teamDao.incrementScore(teamId)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun rotateTeams(winnerId: Long): Result<Unit> {
        return try {
            val allTeams = teamDao.getAllTeamsOnce().toMutableList()
            if (allTeams.size < 2) {
                return Result.Error(Exception("Not enough teams"))
            }

            // Save game result
            val team1 = allTeams[0]
            val team2 = allTeams[1]
            val winner = if (winnerId == team1.id) team1 else team2
            
            val game = GameEntity(
                team1Id = team1.id,
                team1Name = team1.name,
                team1Score = team1.score,
                team2Id = team2.id,
                team2Name = team2.name,
                team2Score = team2.score,
                winnerId = winner.id,
                winnerName = winner.name,
                targetScore = GameConstants.DEFAULT_TARGET_SCORE,
                wasOvertime = team1.score == GameConstants.DEFAULT_TARGET_SCORE - 1 && 
                             team2.score == GameConstants.DEFAULT_TARGET_SCORE - 1
            )
            gameDao.insertGame(game)

            // Move winner to end, loser stays in position 0
            val winnerIndex = allTeams.indexOfFirst { it.id == winnerId }
            if (winnerIndex != -1) {
                val winnerTeam = allTeams.removeAt(winnerIndex)
                allTeams.add(winnerTeam)
            }

            // Reset scores for new first two teams
            if (allTeams.size >= 2) {
                allTeams[0] = allTeams[0].copy(score = 0)
                allTeams[1] = allTeams[1].copy(score = 0)
            }

            // Update positions and save
            allTeams.forEachIndexed { index, team ->
                teamDao.updateTeam(team.copy(position = index))
            }

            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun resetScores(): Result<Unit> {
        return try {
            teamDao.resetAllScores()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getOnCourtTeams(): Result<Pair<Team, Team>> {
        return try {
            val teams = teamDao.getOnCourtTeams()
            if (teams.size < 2) {
                return Result.Error(Exception("Not enough teams on court"))
            }
            Result.Success(Pair(teams[0].toDomain(), teams[1].toDomain()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getQueuedTeams(): Result<List<Team>> {
        return try {
            val teams = teamDao.getAllTeamsOnce()
                .filter { it.position >= 2 }
                .map { it.toDomain() }
            Result.Success(teams)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun checkWinCondition(): Result<Team?> {
        return try {
            val onCourtTeams = teamDao.getOnCourtTeams()
            if (onCourtTeams.size < 2) {
                return Result.Success(null)
            }

            val team1 = onCourtTeams[0]
            val team2 = onCourtTeams[1]
            val targetScore = GameConstants.DEFAULT_TARGET_SCORE

            // Check overtime condition
            if (team1.score == targetScore - 1 && team2.score == targetScore - 1) {
                return Result.Success(null) // Overtime triggered
            }

            // Check win condition
            when {
                team1.score >= targetScore -> Result.Success(team1.toDomain())
                team2.score >= targetScore -> Result.Success(team2.toDomain())
                else -> Result.Success(null)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun handleOvertime(): Result<Unit> {
        return try {
            // In overtime, first to reach overtime_score wins
            // This is handled in the ViewModel
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun TeamEntity.toDomain(): Team {
        return Team(
            id = id,
            name = name,
            score = score,
            position = position
        )
    }
}
