package com.cascarita.app.feature.game.data;

import com.cascarita.app.core.database.GameDao;
import com.cascarita.app.core.database.SettingsDao;
import com.cascarita.app.core.database.TeamDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class GameRepositoryImpl_Factory implements Factory<GameRepositoryImpl> {
  private final Provider<TeamDao> teamDaoProvider;

  private final Provider<GameDao> gameDaoProvider;

  private final Provider<SettingsDao> settingsDaoProvider;

  public GameRepositoryImpl_Factory(Provider<TeamDao> teamDaoProvider,
      Provider<GameDao> gameDaoProvider, Provider<SettingsDao> settingsDaoProvider) {
    this.teamDaoProvider = teamDaoProvider;
    this.gameDaoProvider = gameDaoProvider;
    this.settingsDaoProvider = settingsDaoProvider;
  }

  @Override
  public GameRepositoryImpl get() {
    return newInstance(teamDaoProvider.get(), gameDaoProvider.get(), settingsDaoProvider.get());
  }

  public static GameRepositoryImpl_Factory create(Provider<TeamDao> teamDaoProvider,
      Provider<GameDao> gameDaoProvider, Provider<SettingsDao> settingsDaoProvider) {
    return new GameRepositoryImpl_Factory(teamDaoProvider, gameDaoProvider, settingsDaoProvider);
  }

  public static GameRepositoryImpl newInstance(TeamDao teamDao, GameDao gameDao,
      SettingsDao settingsDao) {
    return new GameRepositoryImpl(teamDao, gameDao, settingsDao);
  }
}
