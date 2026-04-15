package com.cascarita.app.feature.team.domain

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
