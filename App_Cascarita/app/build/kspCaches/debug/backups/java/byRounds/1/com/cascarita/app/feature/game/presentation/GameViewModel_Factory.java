package com.cascarita.app.feature.game.presentation;

import com.cascarita.app.feature.game.domain.CheckWinConditionUseCase;
import com.cascarita.app.feature.game.domain.GetOnCourtTeamsUseCase;
import com.cascarita.app.feature.game.domain.GetQueuedTeamsUseCase;
import com.cascarita.app.feature.game.domain.HandleOvertimeUseCase;
import com.cascarita.app.feature.game.domain.ObserveGameStateUseCase;
import com.cascarita.app.feature.game.domain.ResetScoresUseCase;
import com.cascarita.app.feature.game.domain.RotateTeamsUseCase;
import com.cascarita.app.feature.game.domain.ScorePointUseCase;
import com.cascarita.app.feature.game.domain.UpdateTargetScoreUseCase;
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
public final class GameViewModel_Factory implements Factory<GameViewModel> {
  private final Provider<ScorePointUseCase> scorePointUseCaseProvider;

  private final Provider<RotateTeamsUseCase> rotateTeamsUseCaseProvider;

  private final Provider<ResetScoresUseCase> resetScoresUseCaseProvider;

  private final Provider<GetOnCourtTeamsUseCase> getOnCourtTeamsUseCaseProvider;

  private final Provider<GetQueuedTeamsUseCase> getQueuedTeamsUseCaseProvider;

  private final Provider<CheckWinConditionUseCase> checkWinConditionUseCaseProvider;

  private final Provider<HandleOvertimeUseCase> handleOvertimeUseCaseProvider;

  private final Provider<ObserveGameStateUseCase> observeGameStateUseCaseProvider;

  private final Provider<UpdateTargetScoreUseCase> updateTargetScoreUseCaseProvider;

  private final Provider<RemoveTeamUseCase> removeTeamUseCaseProvider;

  public GameViewModel_Factory(Provider<ScorePointUseCase> scorePointUseCaseProvider,
      Provider<RotateTeamsUseCase> rotateTeamsUseCaseProvider,
      Provider<ResetScoresUseCase> resetScoresUseCaseProvider,
      Provider<GetOnCourtTeamsUseCase> getOnCourtTeamsUseCaseProvider,
      Provider<GetQueuedTeamsUseCase> getQueuedTeamsUseCaseProvider,
      Provider<CheckWinConditionUseCase> checkWinConditionUseCaseProvider,
      Provider<HandleOvertimeUseCase> handleOvertimeUseCaseProvider,
      Provider<ObserveGameStateUseCase> observeGameStateUseCaseProvider,
      Provider<UpdateTargetScoreUseCase> updateTargetScoreUseCaseProvider,
      Provider<RemoveTeamUseCase> removeTeamUseCaseProvider) {
    this.scorePointUseCaseProvider = scorePointUseCaseProvider;
    this.rotateTeamsUseCaseProvider = rotateTeamsUseCaseProvider;
    this.resetScoresUseCaseProvider = resetScoresUseCaseProvider;
    this.getOnCourtTeamsUseCaseProvider = getOnCourtTeamsUseCaseProvider;
    this.getQueuedTeamsUseCaseProvider = getQueuedTeamsUseCaseProvider;
    this.checkWinConditionUseCaseProvider = checkWinConditionUseCaseProvider;
    this.handleOvertimeUseCaseProvider = handleOvertimeUseCaseProvider;
    this.observeGameStateUseCaseProvider = observeGameStateUseCaseProvider;
    this.updateTargetScoreUseCaseProvider = updateTargetScoreUseCaseProvider;
    this.removeTeamUseCaseProvider = removeTeamUseCaseProvider;
  }

  @Override
  public GameViewModel get() {
    return newInstance(scorePointUseCaseProvider.get(), rotateTeamsUseCaseProvider.get(), resetScoresUseCaseProvider.get(), getOnCourtTeamsUseCaseProvider.get(), getQueuedTeamsUseCaseProvider.get(), checkWinConditionUseCaseProvider.get(), handleOvertimeUseCaseProvider.get(), observeGameStateUseCaseProvider.get(), updateTargetScoreUseCaseProvider.get(), removeTeamUseCaseProvider.get());
  }

  public static GameViewModel_Factory create(Provider<ScorePointUseCase> scorePointUseCaseProvider,
      Provider<RotateTeamsUseCase> rotateTeamsUseCaseProvider,
      Provider<ResetScoresUseCase> resetScoresUseCaseProvider,
      Provider<GetOnCourtTeamsUseCase> getOnCourtTeamsUseCaseProvider,
      Provider<GetQueuedTeamsUseCase> getQueuedTeamsUseCaseProvider,
      Provider<CheckWinConditionUseCase> checkWinConditionUseCaseProvider,
      Provider<HandleOvertimeUseCase> handleOvertimeUseCaseProvider,
      Provider<ObserveGameStateUseCase> observeGameStateUseCaseProvider,
      Provider<UpdateTargetScoreUseCase> updateTargetScoreUseCaseProvider,
      Provider<RemoveTeamUseCase> removeTeamUseCaseProvider) {
    return new GameViewModel_Factory(scorePointUseCaseProvider, rotateTeamsUseCaseProvider, resetScoresUseCaseProvider, getOnCourtTeamsUseCaseProvider, getQueuedTeamsUseCaseProvider, checkWinConditionUseCaseProvider, handleOvertimeUseCaseProvider, observeGameStateUseCaseProvider, updateTargetScoreUseCaseProvider, removeTeamUseCaseProvider);
  }

  public static GameViewModel newInstance(ScorePointUseCase scorePointUseCase,
      RotateTeamsUseCase rotateTeamsUseCase, ResetScoresUseCase resetScoresUseCase,
      GetOnCourtTeamsUseCase getOnCourtTeamsUseCase, GetQueuedTeamsUseCase getQueuedTeamsUseCase,
      CheckWinConditionUseCase checkWinConditionUseCase,
      HandleOvertimeUseCase handleOvertimeUseCase, ObserveGameStateUseCase observeGameStateUseCase,
      UpdateTargetScoreUseCase updateTargetScoreUseCase, RemoveTeamUseCase removeTeamUseCase) {
    return new GameViewModel(scorePointUseCase, rotateTeamsUseCase, resetScoresUseCase, getOnCourtTeamsUseCase, getQueuedTeamsUseCase, checkWinConditionUseCase, handleOvertimeUseCase, observeGameStateUseCase, updateTargetScoreUseCase, removeTeamUseCase);
  }
}
