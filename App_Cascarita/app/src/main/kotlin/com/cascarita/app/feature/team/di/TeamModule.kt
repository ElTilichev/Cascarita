package com.cascarita.app.feature.team.di

import com.cascarita.app.feature.team.data.TeamRepositoryImpl
import com.cascarita.app.feature.team.domain.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TeamModule {
    @Binds
    @Singleton
    abstract fun bindTeamRepository(
        repositoryImpl: TeamRepositoryImpl
    ): TeamRepository
}
