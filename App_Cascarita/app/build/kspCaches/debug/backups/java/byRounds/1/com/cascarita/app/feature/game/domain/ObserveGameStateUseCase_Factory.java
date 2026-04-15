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
public final class ObserveGameStateUseCase_Factory implements Factory<ObserveGameStateUseCase> {
  private final Provider<GameRepository> repositoryProvider;

  public ObserveGameStateUseCase_Factory(Provider<GameRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveGameStateUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveGameStateUseCase_Factory create(
      Provider<GameRepository> repositoryProvider) {
    return new ObserveGameStateUseCase_Factory(repositoryProvider);
  }

  public static ObserveGameStateUseCase newInstance(GameRepository repository) {
    return new ObserveGameStateUseCase(repository);
  }
}
