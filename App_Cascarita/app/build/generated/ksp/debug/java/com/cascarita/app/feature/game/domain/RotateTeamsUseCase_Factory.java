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
public final class RotateTeamsUseCase_Factory implements Factory<RotateTeamsUseCase> {
  private final Provider<GameRepository> repositoryProvider;

  public RotateTeamsUseCase_Factory(Provider<GameRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RotateTeamsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static RotateTeamsUseCase_Factory create(Provider<GameRepository> repositoryProvider) {
    return new RotateTeamsUseCase_Factory(repositoryProvider);
  }

  public static RotateTeamsUseCase newInstance(GameRepository repository) {
    return new RotateTeamsUseCase(repository);
  }
}
