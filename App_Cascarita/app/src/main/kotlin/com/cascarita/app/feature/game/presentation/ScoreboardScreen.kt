package com.cascarita.app.feature.game.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material.icons.outlined.SportsVolleyball
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cascarita.app.core.theme.*
import com.cascarita.app.feature.game.domain.Team

@Composable
fun ScoreboardScreen(
    viewModel: GameViewModel = hiltViewModel(),
    onNavigateToTeams: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Surface)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Scoreboard
        item {
            ScoreboardSection(
                team1 = uiState.team1,
                team2 = uiState.team2,
                isOvertime = uiState.isOvertime,
                onScorePoint = { teamId -> viewModel.scorePoint(teamId) }
            )
        }

        // Next Team Banner
        if (uiState.queuedTeams.isNotEmpty()) {
            item {
                NextTeamBanner(team = uiState.queuedTeams.first())
            }
        }

        // Queue Section
        item {
            QueueSection(
                teams = uiState.queuedTeams,
                onRemoveTeam = { teamId -> viewModel.removeTeam(teamId) }
            )
        }

        // Match Settings
        item {
            MatchSettingsSection(
                targetScore = uiState.targetScore,
                onUpdateTargetScore = { viewModel.updateTargetScore(it) },
                onResetScores = { viewModel.resetScores() }
            )
        }

        item {
            Spacer(modifier = Modifier.height(100.dp)) // Space for bottom nav
        }
    }
}

@Composable
fun ScoreboardSection(
    team1: Team?,
    team2: Team?,
    isOvertime: Boolean,
    onScorePoint: (Long) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Team 1
            TeamScoreCard(
                team = team1,
                teamLabel = "LOCAL",
                isHighlighted = false,
                onScorePoint = onScorePoint,
                modifier = Modifier.weight(1f)
            )

            // VS Separator
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .width(2.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, OutlineVariant, Color.Transparent)
                            )
                        )
                )
                Text(
                    text = "VS",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Secondary,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .width(2.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(OutlineVariant, OutlineVariant, Color.Transparent)
                            )
                        )
                )
            }

            // Team 2
            TeamScoreCard(
                team = team2,
                teamLabel = "VISITA",
                isHighlighted = true,
                onScorePoint = onScorePoint,
                modifier = Modifier.weight(1f)
            )
        }

        if (isOvertime) {
            Text(
                text = "¡PRÓRROGA!",
                style = MaterialTheme.typography.labelLarge,
                color = Tertiary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun TeamScoreCard(
    team: Team?,
    teamLabel: String,
    isHighlighted: Boolean,
    onScorePoint: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val cardModifier = if (team != null) {
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerHighest)
            .clickable { onScorePoint(team.id) }
            .padding(24.dp)
    } else {
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerHighest)
            .padding(24.dp)
    }

    Box(
        modifier = cardModifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = (team?.name ?: "EQUIPO ?").uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = if (isHighlighted) Primary else OnSurfaceVariant,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = team?.score?.toString() ?: "0",
                style = MaterialTheme.typography.displaySmall,
                color = OnSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = teamLabel,
                style = MaterialTheme.typography.labelSmall,
                color = Outline
            )
        }
    }
}

@Composable
fun NextTeamBanner(team: Team) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
    ) {
        // Badge
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 12.dp, y = (-8).dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Tertiary)
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = "SIGUIENTE EQUIPO",
                style = MaterialTheme.typography.labelSmall,
                color = OnTertiary,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHighest),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.SportsVolleyball,
                        contentDescription = null,
                        tint = Tertiary
                    )
                }

                Column {
                    Text(
                        text = team.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = OnSurface,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Esperando cancha",
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceVariant
                    )
                }
            }

            IconButton(
                onClick = { /* TODO: Swap to court */ }
            ) {
                Icon(
                    imageVector = Icons.Default.SwapHoriz,
                    contentDescription = "Intercambiar",
                    tint = Secondary
                )
            }
        }
    }
}

@Composable
fun QueueSection(
    teams: List<Team>,
    onRemoveTeam: (Long) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "EN FILA",
                style = MaterialTheme.typography.labelMedium,
                color = OnSurfaceVariant
            )
            Text(
                text = "${teams.size} equipos esperando",
                style = MaterialTheme.typography.labelSmall,
                color = Outline
            )
        }

        teams.forEachIndexed { index, team ->
            QueueTeamItem(
                team = team,
                index = index,
                onRemove = onRemoveTeam
            )
        }
    }
}

@Composable
fun QueueTeamItem(
    team: Team,
    index: Int,
    onRemove: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${index + 1}",
                style = MaterialTheme.typography.titleMedium,
                color = Primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = team.name,
                style = MaterialTheme.typography.bodyLarge,
                color = OnSurface,
                fontWeight = FontWeight.Medium
            )
        }

        IconButton(
            onClick = { onRemove(team.id) }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Eliminar",
                tint = OutlineVariant
            )
        }
    }
}

@Composable
fun MatchSettingsSection(
    targetScore: Int,
    onUpdateTargetScore: (Int) -> Unit,
    onResetScores: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerHigh)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "CONFIGURACIÓN DE JUEGO",
            style = MaterialTheme.typography.labelSmall,
            color = Secondary,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onUpdateTargetScore(targetScore - 1) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerLow)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Menos",
                    tint = OnSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = targetScore.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnSurface,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "PUNTOS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Outline
                )
            }

            IconButton(
                onClick = { onUpdateTargetScore(targetScore + 1) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerLow)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Más",
                    tint = OnSurfaceVariant
                )
            }
        }

        Text(
            text = "Partido a $targetScore puntos",
            style = MaterialTheme.typography.bodyMedium,
            color = OnSurfaceVariant
        )

        OutlinedButton(
            onClick = onResetScores,
            modifier = Modifier
                .clip(CircleShape)
                .padding(top = 8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Error
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.RestartAlt,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "REINICIAR TODO",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
