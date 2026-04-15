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
public final class RemoveTeamUseCase_Factory implements Factory<RemoveTeamUseCase> {
  private final Provider<TeamRepository> repositoryProvider;

  public RemoveTeamUseCase_Factory(Provider<TeamRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RemoveTeamUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static RemoveTeamUseCase_Factory create(Provider<TeamRepository> repositoryProvider) {
    return new RemoveTeamUseCase_Factory(repositoryProvider);
  }

  public static RemoveTeamUseCase newInstance(TeamRepository repository) {
    return new RemoveTeamUseCase(repository);
  }
}
