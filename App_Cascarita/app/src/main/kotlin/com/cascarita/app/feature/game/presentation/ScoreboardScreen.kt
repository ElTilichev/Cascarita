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
        verticalArrangement = Arrangement.spacedBy(32.dp) // Increased spacing
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
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
        if (uiState.queuedTeams.size > 1) {
            item {
                QueueSection(
                    teams = uiState.queuedTeams.drop(1), // Show rest of queue
                    onRemoveTeam = { teamId -> viewModel.removeTeam(teamId) }
                )
            }
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
            Spacer(modifier = Modifier.height(32.dp))
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
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp) // Increased spacing
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Increased spacing
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
            Text(
                text = "VS",
                style = MaterialTheme.typography.titleLarge, // Slightly larger
                color = Secondary.copy(alpha = 0.7f),
                fontWeight = FontWeight.Black
            )

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
            Surface(
                color = Tertiary.copy(alpha = 0.15f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "¡MUERTE SÚBITA!",
                    style = MaterialTheme.typography.labelLarge,
                    color = Tertiary,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(12.dp)
                )
            }
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
    val backgroundColor = if (isHighlighted) SurfaceContainerHigh else SurfaceContainerHighest
    
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(24.dp), // More rounded
        modifier = if (team != null) {
            modifier.clickable { onScorePoint(team.id) }
        } else {
            modifier
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 12.dp)
        ) {
            Surface(
                color = if (isHighlighted) Primary.copy(alpha = 0.1f) else Outline.copy(alpha = 0.1f),
                shape = CircleShape
            ) {
                Text(
                    text = teamLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isHighlighted) Primary else Outline,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }

            Text(
                text = team?.score?.toString() ?: "0",
                style = MaterialTheme.typography.displayMedium,
                color = OnSurface,
                fontWeight = FontWeight.Black
            )

            Text(
                text = (team?.name ?: "ESPERANDO").uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = OnSurfaceVariant,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

@Composable
fun NextTeamBanner(team: Team) {
    Surface(
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Badge
            Surface(
                color = Tertiary,
                shape = RoundedCornerShape(bottomEnd = 12.dp),
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    text = " SIGUIENTE ",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnTertiary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = SurfaceContainerHighest,
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Outlined.SportsVolleyball,
                                contentDescription = null,
                                tint = Tertiary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Column {
                        Text(
                            text = team.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = OnSurface,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "En espera",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.SwapHoriz,
                    contentDescription = null,
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

        Text(
            text = "v0.1.3",
            style = MaterialTheme.typography.labelSmall,
            color = Outline.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
