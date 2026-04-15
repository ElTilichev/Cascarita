package com.cascarita.app.feature.game.di

import com.cascarita.app.feature.game.data.GameRepositoryImpl
import com.cascarita.app.feature.game.domain.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GameModule {
    @Binds
    @Singleton
    abstract fun bindGameRepository(
        repositoryImpl: GameRepositoryImpl
    ): GameRepository
}
