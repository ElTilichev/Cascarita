package com.cascarita.app.feature.game.presentation

import com.cascarita.app.feature.game.domain.Team
import org.junit.Test
import org.junit.Assert.*

class GameViewModelTest {

    @Test
    fun testTeamCreation() {
        val team = Team(id = 1, name = "Equipo 1", score = 0, position = 0)
        assertEquals("Equipo 1", team.name)
        assertEquals(0, team.score)
    }

    @Test
    fun testScoreIncrement() {
        val team = Team(id = 1, name = "Equipo 1", score = 0, position = 0)
        val newScore = team.score + 1
        assertEquals(1, newScore)
    }
}
