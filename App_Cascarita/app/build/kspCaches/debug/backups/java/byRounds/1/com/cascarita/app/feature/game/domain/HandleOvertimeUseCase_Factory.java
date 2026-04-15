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
public final class HandleOvertimeUseCase_Factory implements Factory<HandleOvertimeUseCase> {
  private final Provider<GameRepository> repositoryProvider;

  public HandleOvertimeUseCase_Factory(Provider<GameRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public HandleOvertimeUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static HandleOvertimeUseCase_Factory create(Provider<GameRepository> repositoryProvider) {
    return new HandleOvertimeUseCase_Factory(repositoryProvider);
  }

  public static HandleOvertimeUseCase newInstance(GameRepository repository) {
    return new HandleOvertimeUseCase(repository);
  }
}
