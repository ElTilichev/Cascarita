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
public final class UpdateTargetScoreUseCase_Factory implements Factory<UpdateTargetScoreUseCase> {
  private final Provider<GameRepository> repositoryProvider;

  public UpdateTargetScoreUseCase_Factory(Provider<GameRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateTargetScoreUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateTargetScoreUseCase_Factory create(
      Provider<GameRepository> repositoryProvider) {
    return new UpdateTargetScoreUseCase_Factory(repositoryProvider);
  }

  public static UpdateTargetScoreUseCase newInstance(GameRepository repository) {
    return new UpdateTargetScoreUseCase(repository);
  }
}
