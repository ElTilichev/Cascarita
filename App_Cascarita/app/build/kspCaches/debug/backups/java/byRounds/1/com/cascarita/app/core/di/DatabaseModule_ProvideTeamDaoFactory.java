package com.cascarita.app.core.di;

import com.cascarita.app.core.database.CascaritaDatabase;
import com.cascarita.app.core.database.TeamDao;
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
public final class DatabaseModule_ProvideTeamDaoFactory implements Factory<TeamDao> {
  private final Provider<CascaritaDatabase> databaseProvider;

  public DatabaseModule_ProvideTeamDaoFactory(Provider<CascaritaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TeamDao get() {
    return provideTeamDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideTeamDaoFactory create(
      Provider<CascaritaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideTeamDaoFactory(databaseProvider);
  }

  public static TeamDao provideTeamDao(CascaritaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTeamDao(database));
  }
}
