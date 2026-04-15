package com.cascarita.app.feature.team.domain;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ObserveTeamListUseCase_Factory implements Factory<ObserveTeamListUseCase> {
  private final Provider<TeamRepository> repositoryProvider;

  public ObserveTeamListUseCase_Factory(Provider<TeamRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveTeamListUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveTeamListUseCase_Factory create(Provider<TeamRepository> repositoryProvider) {
    return new ObserveTeamListUseCase_Factory(repositoryProvider);
  }

  public static ObserveTeamListUseCase newInstance(TeamRepository repository) {
    return new ObserveTeamListUseCase(repository);
  }
}
