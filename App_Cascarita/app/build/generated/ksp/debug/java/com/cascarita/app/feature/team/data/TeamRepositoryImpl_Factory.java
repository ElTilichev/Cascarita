package com.cascarita.app.feature.team.data;

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
public final class TeamRepositoryImpl_Factory implements Factory<TeamRepositoryImpl> {
  private final Provider<TeamDao> teamDaoProvider;

  public TeamRepositoryImpl_Factory(Provider<TeamDao> teamDaoProvider) {
    this.teamDaoProvider = teamDaoProvider;
  }

  @Override
  public TeamRepositoryImpl get() {
    return newInstance(teamDaoProvider.get());
  }

  public static TeamRepositoryImpl_Factory create(Provider<TeamDao> teamDaoProvider) {
    return new TeamRepositoryImpl_Factory(teamDaoProvider);
  }

  public static TeamRepositoryImpl newInstance(TeamDao teamDao) {
    return new TeamRepositoryImpl(teamDao);
  }
}
