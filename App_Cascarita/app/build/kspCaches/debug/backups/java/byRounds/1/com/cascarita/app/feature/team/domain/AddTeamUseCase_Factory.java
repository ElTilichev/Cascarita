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
public final class AddTeamUseCase_Factory implements Factory<AddTeamUseCase> {
  private final Provider<TeamRepository> repositoryProvider;

  public AddTeamUseCase_Factory(Provider<TeamRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddTeamUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddTeamUseCase_Factory create(Provider<TeamRepository> repositoryProvider) {
    return new AddTeamUseCase_Factory(repositoryProvider);
  }

  public static AddTeamUseCase newInstance(TeamRepository repository) {
    return new AddTeamUseCase(repository);
  }
}
