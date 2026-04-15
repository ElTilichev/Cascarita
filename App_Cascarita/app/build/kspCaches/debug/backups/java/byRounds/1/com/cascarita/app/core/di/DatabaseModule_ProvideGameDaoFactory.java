package com.cascarita.app.core.di;

import com.cascarita.app.core.database.CascaritaDatabase;
import com.cascarita.app.core.database.GameDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideGameDaoFactory implements Factory<GameDao> {
  private final Provider<CascaritaDatabase> databaseProvider;

  public DatabaseModule_ProvideGameDaoFactory(Provider<CascaritaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public GameDao get() {
    return provideGameDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideGameDaoFactory create(
      Provider<CascaritaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideGameDaoFactory(databaseProvider);
  }

  public static GameDao provideGameDao(CascaritaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideGameDao(database));
  }
}
