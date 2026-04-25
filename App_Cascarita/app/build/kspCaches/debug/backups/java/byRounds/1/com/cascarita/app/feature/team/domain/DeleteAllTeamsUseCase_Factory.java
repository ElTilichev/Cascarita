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
public final class DeleteAllTeamsUseCase_Factory implements Factory<DeleteAllTeamsUseCase> {
  private final Provider<TeamRepository> repositoryProvider;

  public DeleteAllTeamsUseCase_Factory(Provider<TeamRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteAllTeamsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteAllTeamsUseCase_Factory create(Provider<TeamRepository> repositoryProvider) {
    return new DeleteAllTeamsUseCase_Factory(repositoryProvider);
  }

  public static DeleteAllTeamsUseCase newInstance(TeamRepository repository) {
    return new DeleteAllTeamsUseCase(repository);
  }
}
