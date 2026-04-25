package com.cascarita.app.feature.team.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cascarita.app.core.theme.*
import com.cascarita.app.feature.team.domain.TeamDetail

@Composable
fun TeamsScreen(
    viewModel: TeamViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Surface)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Hero Section
            item {
                HeroSection(
                    onDeleteAll = { viewModel.deleteAllTeams() },
                    showDelete = uiState.onCourtTeams.isNotEmpty() || uiState.queuedTeams.isNotEmpty()
                )
            }

            // On Court Teams
            items(uiState.onCourtTeams) { team ->
                OnCourtTeamCard(team = team)
            }

            // Stats Break
            item {
                StatsSection(totalWaiting = uiState.totalWaiting)
            }

            // Queue Header
            if (uiState.queuedTeams.isNotEmpty()) {
                item {
                    Text(
                        text = "FILA DE ESPERA",
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurfaceVariant,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )
                }
            }

            // Queued Teams
            items(uiState.queuedTeams) { team ->
                QueuedTeamCard(
                    team = team,
                    onRemove = { viewModel.removeTeam(team.id) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(120.dp)) // Space for FAB and bottom nav
            }
        }

        // FAB
        FloatingActionButton(
            onClick = { viewModel.showAddTeamDialog() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 100.dp),
            containerColor = Primary,
            contentColor = OnPrimary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar equipo"
            )
        }

        // Add Team Dialog
        if (uiState.showAddTeamDialog) {
            AddTeamDialog(
                onDismiss = { viewModel.hideAddTeamDialog() },
                onConfirm = { name, captain ->
                    viewModel.addTeam(name, captain)
                }
            )
        }
    }
}

@Composable
fun HeroSection(
    onDeleteAll: () -> Unit,
    showDelete: Boolean
) {
    Surface(
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "EQUIPOS",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnSurface,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Gestiona las retas del día",
                    style = MaterialTheme.typography.bodyLarge,
                    color = OnSurfaceVariant.copy(alpha = 0.8f)
                )
            }

            if (showDelete) {
                IconButton(
                    onClick = onDeleteAll,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Error.copy(alpha = 0.1f))
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteSweep,
                        contentDescription = "Borrar todos",
                        tint = Error
                    )
                }
            }
        }
    }
}

@Composable
fun OnCourtTeamCard(team: TeamDetail) {
    Surface(
        color = SurfaceContainerHighest,
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, Primary.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.size(56.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.SportsSoccer,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Column {
                    Text(
                        text = team.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = OnSurface,
                        fontWeight = FontWeight.Black
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Secondary,
                            shape = CircleShape,
                            modifier = Modifier.size(10.dp)
                        ) {}
                        Text(
                            text = "JUGANDO",
                            style = MaterialTheme.typography.labelMedium,
                            color = Secondary,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatsSection(totalWaiting: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Waiting Teams Card
        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = SurfaceContainerLow
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Outlined.Groups,
                    contentDescription = null,
                    tint = Secondary
                )
                Text(
                    text = totalWaiting.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnSurface,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "EN ESPERA",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun QueuedTeamCard(
    team: TeamDetail,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLow)
            .padding(20.dp),
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
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerHighest),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Shield,
                    contentDescription = null,
                    tint = OnSurfaceVariant
                )
            }

            Column {
                Text(
                    text = team.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = OnSurface,
                    fontWeight = FontWeight.Bold
                )
                if (team.captain != null) {
                    Text(
                        text = "Capitán: ${team.captain}",
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceVariant
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = onRemove
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Eliminar",
                    tint = OutlineVariant
                )
            }
        }
    }
}

@Composable
fun AddTeamDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String?) -> Unit
) {
    var teamName by remember { mutableStateOf("") }
    var captainName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Agregar Equipo",
                style = MaterialTheme.typography.titleLarge,
                color = OnSurface
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = teamName,
                    onValueChange = { teamName = it },
                    label = { Text("Nombre del equipo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        focusedLabelColor = Primary
                    )
                )
                OutlinedTextField(
                    value = captainName,
                    onValueChange = { captainName = it },
                    label = { Text("Nombre del capitán (opcional)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        focusedLabelColor = Primary
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (teamName.isNotBlank()) {
                        onConfirm(teamName, captainName.ifBlank { null })
                    }
                },
                enabled = teamName.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                )
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        containerColor = SurfaceContainerHigh
    )
}
