package com.cascarita.app.core.di;

import com.cascarita.app.core.database.CascaritaDatabase;
import com.cascarita.app.core.database.SettingsDao;
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
public final class DatabaseModule_ProvideSettingsDaoFactory implements Factory<SettingsDao> {
  private final Provider<CascaritaDatabase> databaseProvider;

  public DatabaseModule_ProvideSettingsDaoFactory(Provider<CascaritaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SettingsDao get() {
    return provideSettingsDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideSettingsDaoFactory create(
      Provider<CascaritaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideSettingsDaoFactory(databaseProvider);
  }

  public static SettingsDao provideSettingsDao(CascaritaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSettingsDao(database));
  }
}
