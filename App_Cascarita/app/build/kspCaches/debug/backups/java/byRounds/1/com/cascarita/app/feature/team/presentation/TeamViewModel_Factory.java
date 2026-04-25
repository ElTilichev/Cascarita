package com.cascarita.app.feature.team.presentation;

import com.cascarita.app.feature.team.domain.AddTeamUseCase;
import com.cascarita.app.feature.team.domain.DeleteAllTeamsUseCase;
import com.cascarita.app.feature.team.domain.ObserveTeamListUseCase;
import com.cascarita.app.feature.team.domain.RemoveTeamUseCase;
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
public final class TeamViewModel_Factory implements Factory<TeamViewModel> {
  private final Provider<AddTeamUseCase> addTeamUseCaseProvider;

  private final Provider<RemoveTeamUseCase> removeTeamUseCaseProvider;

  private final Provider<DeleteAllTeamsUseCase> deleteAllTeamsUseCaseProvider;

  private final Provider<ObserveTeamListUseCase> observeTeamListUseCaseProvider;

  public TeamViewModel_Factory(Provider<AddTeamUseCase> addTeamUseCaseProvider,
      Provider<RemoveTeamUseCase> removeTeamUseCaseProvider,
      Provider<DeleteAllTeamsUseCase> deleteAllTeamsUseCaseProvider,
      Provider<ObserveTeamListUseCase> observeTeamListUseCaseProvider) {
    this.addTeamUseCaseProvider = addTeamUseCaseProvider;
    this.removeTeamUseCaseProvider = removeTeamUseCaseProvider;
    this.deleteAllTeamsUseCaseProvider = deleteAllTeamsUseCaseProvider;
    this.observeTeamListUseCaseProvider = observeTeamListUseCaseProvider;
  }

  @Override
  public TeamViewModel get() {
    return newInstance(addTeamUseCaseProvider.get(), removeTeamUseCaseProvider.get(), deleteAllTeamsUseCaseProvider.get(), observeTeamListUseCaseProvider.get());
  }

  public static TeamViewModel_Factory create(Provider<AddTeamUseCase> addTeamUseCaseProvider,
      Provider<RemoveTeamUseCase> removeTeamUseCaseProvider,
      Provider<DeleteAllTeamsUseCase> deleteAllTeamsUseCaseProvider,
      Provider<ObserveTeamListUseCase> observeTeamListUseCaseProvider) {
    return new TeamViewModel_Factory(addTeamUseCaseProvider, removeTeamUseCaseProvider, deleteAllTeamsUseCaseProvider, observeTeamListUseCaseProvider);
  }

  public static TeamViewModel newInstance(AddTeamUseCase addTeamUseCase,
      RemoveTeamUseCase removeTeamUseCase, DeleteAllTeamsUseCase deleteAllTeamsUseCase,
      ObserveTeamListUseCase observeTeamListUseCase) {
    return new TeamViewModel(addTeamUseCase, removeTeamUseCase, deleteAllTeamsUseCase, observeTeamListUseCase);
  }
}
