package com.cascarita.app.core.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CascaritaDatabase_Impl extends CascaritaDatabase {
  private volatile TeamDao _teamDao;

  private volatile GameDao _gameDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `team` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `score` INTEGER NOT NULL, `position` INTEGER NOT NULL, `captain` TEXT, `isActive` INTEGER NOT NULL, `isOnCourt` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `game` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `team1Id` INTEGER NOT NULL, `team1Name` TEXT NOT NULL, `team1Score` INTEGER NOT NULL, `team2Id` INTEGER NOT NULL, `team2Name` TEXT NOT NULL, `team2Score` INTEGER NOT NULL, `winnerId` INTEGER NOT NULL, `winnerName` TEXT NOT NULL, `targetScore` INTEGER NOT NULL, `wasOvertime` INTEGER NOT NULL, `completedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '08dc2fe9a57bf0de756658bff7217e1e')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `team`");
        db.execSQL("DROP TABLE IF EXISTS `game`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTeam = new HashMap<String, TableInfo.Column>(8);
        _columnsTeam.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeam.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeam.put("score", new TableInfo.Column("score", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeam.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeam.put("captain", new TableInfo.Column("captain", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeam.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeam.put("isOnCourt", new TableInfo.Column("isOnCourt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeam.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTeam = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTeam = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTeam = new TableInfo("team", _columnsTeam, _foreignKeysTeam, _indicesTeam);
        final TableInfo _existingTeam = TableInfo.read(db, "team");
        if (!_infoTeam.equals(_existingTeam)) {
          return new RoomOpenHelper.ValidationResult(false, "team(com.cascarita.app.core.database.TeamEntity).\n"
                  + " Expected:\n" + _infoTeam + "\n"
                  + " Found:\n" + _existingTeam);
        }
        final HashMap<String, TableInfo.Column> _columnsGame = new HashMap<String, TableInfo.Column>(12);
        _columnsGame.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("team1Id", new TableInfo.Column("team1Id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("team1Name", new TableInfo.Column("team1Name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("team1Score", new TableInfo.Column("team1Score", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("team2Id", new TableInfo.Column("team2Id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("team2Name", new TableInfo.Column("team2Name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("team2Score", new TableInfo.Column("team2Score", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("winnerId", new TableInfo.Column("winnerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("winnerName", new TableInfo.Column("winnerName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("targetScore", new TableInfo.Column("targetScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("wasOvertime", new TableInfo.Column("wasOvertime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGame.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGame = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGame = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGame = new TableInfo("game", _columnsGame, _foreignKeysGame, _indicesGame);
        final TableInfo _existingGame = TableInfo.read(db, "game");
        if (!_infoGame.equals(_existingGame)) {
          return new RoomOpenHelper.ValidationResult(false, "game(com.cascarita.app.core.database.GameEntity).\n"
                  + " Expected:\n" + _infoGame + "\n"
                  + " Found:\n" + _existingGame);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "08dc2fe9a57bf0de756658bff7217e1e", "3cca45d6f67b774b12952b5e4bd9d020");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "team","game");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `team`");
      _db.execSQL("DELETE FROM `game`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TeamDao.class, TeamDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GameDao.class, GameDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public TeamDao teamDao() {
    if (_teamDao != null) {
      return _teamDao;
    } else {
      synchronized(this) {
        if(_teamDao == null) {
          _teamDao = new TeamDao_Impl(this);
        }
        return _teamDao;
      }
    }
  }

  @Override
  public GameDao gameDao() {
    if (_gameDao != null) {
      return _gameDao;
    } else {
      synchronized(this) {
        if(_gameDao == null) {
          _gameDao = new GameDao_Impl(this);
        }
        return _gameDao;
      }
    }
  }
}
