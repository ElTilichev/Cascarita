package com.cascarita.app.core.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GameDao_Impl implements GameDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GameEntity> __insertionAdapterOfGameEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllGames;

  public GameDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGameEntity = new EntityInsertionAdapter<GameEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `game` (`id`,`team1Id`,`team1Name`,`team1Score`,`team2Id`,`team2Name`,`team2Score`,`winnerId`,`winnerName`,`targetScore`,`wasOvertime`,`completedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GameEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTeam1Id());
        statement.bindString(3, entity.getTeam1Name());
        statement.bindLong(4, entity.getTeam1Score());
        statement.bindLong(5, entity.getTeam2Id());
        statement.bindString(6, entity.getTeam2Name());
        statement.bindLong(7, entity.getTeam2Score());
        statement.bindLong(8, entity.getWinnerId());
        statement.bindString(9, entity.getWinnerName());
        statement.bindLong(10, entity.getTargetScore());
        final int _tmp = entity.getWasOvertime() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindLong(12, entity.getCompletedAt());
      }
    };
    this.__preparedStmtOfDeleteAllGames = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM game";
        return _query;
      }
    };
  }

  @Override
  public Object insertGame(final GameEntity game, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGameEntity.insert(game);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllGames(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllGames.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllGames.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<GameEntity>> getAllGames() {
    final String _sql = "SELECT * FROM game ORDER BY completedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"game"}, new Callable<List<GameEntity>>() {
      @Override
      @NonNull
      public List<GameEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTeam1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "team1Id");
          final int _cursorIndexOfTeam1Name = CursorUtil.getColumnIndexOrThrow(_cursor, "team1Name");
          final int _cursorIndexOfTeam1Score = CursorUtil.getColumnIndexOrThrow(_cursor, "team1Score");
          final int _cursorIndexOfTeam2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "team2Id");
          final int _cursorIndexOfTeam2Name = CursorUtil.getColumnIndexOrThrow(_cursor, "team2Name");
          final int _cursorIndexOfTeam2Score = CursorUtil.getColumnIndexOrThrow(_cursor, "team2Score");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfWinnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerName");
          final int _cursorIndexOfTargetScore = CursorUtil.getColumnIndexOrThrow(_cursor, "targetScore");
          final int _cursorIndexOfWasOvertime = CursorUtil.getColumnIndexOrThrow(_cursor, "wasOvertime");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<GameEntity> _result = new ArrayList<GameEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GameEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTeam1Id;
            _tmpTeam1Id = _cursor.getLong(_cursorIndexOfTeam1Id);
            final String _tmpTeam1Name;
            _tmpTeam1Name = _cursor.getString(_cursorIndexOfTeam1Name);
            final int _tmpTeam1Score;
            _tmpTeam1Score = _cursor.getInt(_cursorIndexOfTeam1Score);
            final long _tmpTeam2Id;
            _tmpTeam2Id = _cursor.getLong(_cursorIndexOfTeam2Id);
            final String _tmpTeam2Name;
            _tmpTeam2Name = _cursor.getString(_cursorIndexOfTeam2Name);
            final int _tmpTeam2Score;
            _tmpTeam2Score = _cursor.getInt(_cursorIndexOfTeam2Score);
            final long _tmpWinnerId;
            _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            final String _tmpWinnerName;
            _tmpWinnerName = _cursor.getString(_cursorIndexOfWinnerName);
            final int _tmpTargetScore;
            _tmpTargetScore = _cursor.getInt(_cursorIndexOfTargetScore);
            final boolean _tmpWasOvertime;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfWasOvertime);
            _tmpWasOvertime = _tmp != 0;
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            _item = new GameEntity(_tmpId,_tmpTeam1Id,_tmpTeam1Name,_tmpTeam1Score,_tmpTeam2Id,_tmpTeam2Name,_tmpTeam2Score,_tmpWinnerId,_tmpWinnerName,_tmpTargetScore,_tmpWasOvertime,_tmpCompletedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<GameEntity>> getRecentGames() {
    final String _sql = "SELECT * FROM game ORDER BY completedAt DESC LIMIT 10";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"game"}, new Callable<List<GameEntity>>() {
      @Override
      @NonNull
      public List<GameEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTeam1Id = CursorUtil.getColumnIndexOrThrow(_cursor, "team1Id");
          final int _cursorIndexOfTeam1Name = CursorUtil.getColumnIndexOrThrow(_cursor, "team1Name");
          final int _cursorIndexOfTeam1Score = CursorUtil.getColumnIndexOrThrow(_cursor, "team1Score");
          final int _cursorIndexOfTeam2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "team2Id");
          final int _cursorIndexOfTeam2Name = CursorUtil.getColumnIndexOrThrow(_cursor, "team2Name");
          final int _cursorIndexOfTeam2Score = CursorUtil.getColumnIndexOrThrow(_cursor, "team2Score");
          final int _cursorIndexOfWinnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerId");
          final int _cursorIndexOfWinnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "winnerName");
          final int _cursorIndexOfTargetScore = CursorUtil.getColumnIndexOrThrow(_cursor, "targetScore");
          final int _cursorIndexOfWasOvertime = CursorUtil.getColumnIndexOrThrow(_cursor, "wasOvertime");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<GameEntity> _result = new ArrayList<GameEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GameEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTeam1Id;
            _tmpTeam1Id = _cursor.getLong(_cursorIndexOfTeam1Id);
            final String _tmpTeam1Name;
            _tmpTeam1Name = _cursor.getString(_cursorIndexOfTeam1Name);
            final int _tmpTeam1Score;
            _tmpTeam1Score = _cursor.getInt(_cursorIndexOfTeam1Score);
            final long _tmpTeam2Id;
            _tmpTeam2Id = _cursor.getLong(_cursorIndexOfTeam2Id);
            final String _tmpTeam2Name;
            _tmpTeam2Name = _cursor.getString(_cursorIndexOfTeam2Name);
            final int _tmpTeam2Score;
            _tmpTeam2Score = _cursor.getInt(_cursorIndexOfTeam2Score);
            final long _tmpWinnerId;
            _tmpWinnerId = _cursor.getLong(_cursorIndexOfWinnerId);
            final String _tmpWinnerName;
            _tmpWinnerName = _cursor.getString(_cursorIndexOfWinnerName);
            final int _tmpTargetScore;
            _tmpTargetScore = _cursor.getInt(_cursorIndexOfTargetScore);
            final boolean _tmpWasOvertime;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfWasOvertime);
            _tmpWasOvertime = _tmp != 0;
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            _item = new GameEntity(_tmpId,_tmpTeam1Id,_tmpTeam1Name,_tmpTeam1Score,_tmpTeam2Id,_tmpTeam2Name,_tmpTeam2Score,_tmpWinnerId,_tmpWinnerName,_tmpTargetScore,_tmpWasOvertime,_tmpCompletedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getGameCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM game";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
