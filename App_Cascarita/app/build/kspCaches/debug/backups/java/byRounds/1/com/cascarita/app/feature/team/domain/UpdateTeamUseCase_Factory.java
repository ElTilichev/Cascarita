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
public final class UpdateTeamUseCase_Factory implements Factory<UpdateTeamUseCase> {
  private final Provider<TeamRepository> repositoryProvider;

  public UpdateTeamUseCase_Factory(Provider<TeamRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateTeamUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateTeamUseCase_Factory create(Provider<TeamRepository> repositoryProvider) {
    return new UpdateTeamUseCase_Factory(repositoryProvider);
  }

  public static UpdateTeamUseCase newInstance(TeamRepository repository) {
    return new UpdateTeamUseCase(repository);
  }
}
