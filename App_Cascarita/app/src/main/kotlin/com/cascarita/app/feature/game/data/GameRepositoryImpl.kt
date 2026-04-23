package com.cascarita.app.feature.game.data

import com.cascarita.app.core.common.GameConstants
import com.cascarita.app.core.common.Result
import com.cascarita.app.core.database.GameDao
import com.cascarita.app.core.database.GameEntity
import com.cascarita.app.core.database.TeamDao
import com.cascarita.app.core.database.TeamEntity
import com.cascarita.app.core.database.SettingsDao
import com.cascarita.app.core.database.SettingsEntity
import com.cascarita.app.feature.game.domain.GameRepository
import com.cascarita.app.feature.game.domain.GameState
import com.cascarita.app.feature.game.domain.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl @Inject constructor(
    private val teamDao: TeamDao,
    private val gameDao: GameDao,
    private val settingsDao: SettingsDao
) : GameRepository {

    override fun observeGameState(): Flow<GameState> {
        return combine(
            teamDao.getAllTeams(),
            settingsDao.observeSettings()
        ) { teamEntities, settingsEntity ->
            val targetScore = settingsEntity?.targetScore ?: GameConstants.DEFAULT_TARGET_SCORE
            val teams = teamEntities.map { it.toDomain() }
            val onCourtTeams = teams.filter { it.position < 2 }
            
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

            val settings = settingsDao.getSettings()
            val targetScore = settings?.targetScore ?: GameConstants.DEFAULT_TARGET_SCORE

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
                targetScore = targetScore,
                wasOvertime = team1.score == targetScore - 1 && 
                             team2.score == targetScore - 1
            )
            gameDao.insertGame(game)

            // Move winner to end, loser stays in position 0 (or moves to end too? usually loser goes to end)
            // Error report says: "Aunado al error anterior, la aplicacion ya no puede subir el puntaje de ninguno de los dos equipos, tampoco podemos agregar equipos, quitarlos ni moverlos"
            // Let's implement a standard rotation: Loser goes to end of queue. Winner stays on court? 
            // Usually in "cascaritas", winner stays, loser goes to end. 
            // Or maybe both go to end?
            
            val loserId = if (winnerId == team1.id) team2.id else team1.id
            val loserIndex = allTeams.indexOfFirst { it.id == loserId }
            if (loserIndex != -1) {
                val loserTeam = allTeams.removeAt(loserIndex)
                allTeams.add(loserTeam)
            }

            // Reset scores for all teams to be safe, or just the ones moving
            allTeams.forEachIndexed { index, team ->
                val updatedTeam = team.copy(
                    position = index,
                    score = if (index >= 2 || team.id == loserId) 0 else team.score
                )
                teamDao.updateTeam(updatedTeam)
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

            val settings = settingsDao.getSettings()
            val targetScore = settings?.targetScore ?: GameConstants.DEFAULT_TARGET_SCORE

            val team1 = onCourtTeams[0]
            val team2 = onCourtTeams[1]

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
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateTargetScore(targetScore: Int): Result<Unit> {
        return try {
            val currentSettings = settingsDao.getSettings()
            if (currentSettings == null) {
                settingsDao.insertSettings(SettingsEntity(targetScore = targetScore))
            } else {
                settingsDao.updateTargetScore(targetScore)
            }
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
