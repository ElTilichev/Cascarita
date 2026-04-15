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
public final class CheckWinConditionUseCase_Factory implements Factory<CheckWinConditionUseCase> {
  private final Provider<GameRepository> repositoryProvider;

  public CheckWinConditionUseCase_Factory(Provider<GameRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CheckWinConditionUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static CheckWinConditionUseCase_Factory create(
      Provider<GameRepository> repositoryProvider) {
    return new CheckWinConditionUseCase_Factory(repositoryProvider);
  }

  public static CheckWinConditionUseCase newInstance(GameRepository repository) {
    return new CheckWinConditionUseCase(repository);
  }
}
