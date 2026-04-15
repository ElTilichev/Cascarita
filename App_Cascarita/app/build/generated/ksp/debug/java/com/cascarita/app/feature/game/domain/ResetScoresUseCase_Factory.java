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
public final class ResetScoresUseCase_Factory implements Factory<ResetScoresUseCase> {
  private final Provider<GameRepository> repositoryProvider;

  public ResetScoresUseCase_Factory(Provider<GameRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ResetScoresUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ResetScoresUseCase_Factory create(Provider<GameRepository> repositoryProvider) {
    return new ResetScoresUseCase_Factory(repositoryProvider);
  }

  public static ResetScoresUseCase newInstance(GameRepository repository) {
    return new ResetScoresUseCase(repository);
  }
}
