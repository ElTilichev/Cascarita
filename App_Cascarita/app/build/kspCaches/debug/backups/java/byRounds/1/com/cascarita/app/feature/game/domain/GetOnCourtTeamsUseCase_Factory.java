package com.cascarita.app.feature.game.domain;

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
public final class GetOnCourtTeamsUseCase_Factory implements Factory<GetOnCourtTeamsUseCase> {
  private final Provider<GameRepository> repositoryProvider;

  public GetOnCourtTeamsUseCase_Factory(Provider<GameRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetOnCourtTeamsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetOnCourtTeamsUseCase_Factory create(Provider<GameRepository> repositoryProvider) {
    return new GetOnCourtTeamsUseCase_Factory(repositoryProvider);
  }

  public static GetOnCourtTeamsUseCase newInstance(GameRepository repository) {
    return new GetOnCourtTeamsUseCase(repository);
  }
}
