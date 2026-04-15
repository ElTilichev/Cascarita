package com.cascarita.app.core.di

import android.content.Context
import androidx.room.Room
import com.cascarita.app.core.database.CascaritaDatabase
import com.cascarita.app.core.database.GameDao
import com.cascarita.app.core.database.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CascaritaDatabase {
        return Room.databaseBuilder(
            context,
            CascaritaDatabase::class.java,
            "cascarita_database"
        ).build()
    }

    @Provides
    fun provideTeamDao(database: CascaritaDatabase): TeamDao {
        return database.teamDao()
    }

    @Provides
    fun provideGameDao(database: CascaritaDatabase): GameDao {
        return database.gameDao()
    }
}
