package com.example.vitorizkiimanda.footballschedulevri.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavouriteMatch.TABLE_FAVORITE_MATCH, true,
                FavouriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavouriteMatch.EVENT_ID to TEXT + UNIQUE,
                FavouriteMatch.EVENT_DATE to TEXT,
                FavouriteMatch.HOME_ID to TEXT,
                FavouriteMatch.HOME_NAME to TEXT,
                FavouriteMatch.HOME_SCORE to TEXT,
                FavouriteMatch.HOME_GOALS to TEXT,
                FavouriteMatch.HOME_SHOTS to TEXT,
                FavouriteMatch.HOME_KEEPER to TEXT,
                FavouriteMatch.HOME_DEFENSE to TEXT,
                FavouriteMatch.HOME_MIDFIELD to TEXT,
                FavouriteMatch.HOME_FORWARD to TEXT,
                FavouriteMatch.HOME_SUBS to TEXT,
                FavouriteMatch.AWAY_ID to TEXT,
                FavouriteMatch.AWAY_NAME to TEXT,
                FavouriteMatch.AWAY_SCORE to TEXT,
                FavouriteMatch.AWAY_GOALS to TEXT,
                FavouriteMatch.AWAY_SHOTS to TEXT,
                FavouriteMatch.AWAY_KEEPER to TEXT,
                FavouriteMatch.AWAY_DEFENSE to TEXT,
                FavouriteMatch.AWAY_MIDFIELD to TEXT,
                FavouriteMatch.AWAY_FORWARD to TEXT,
                FavouriteMatch.AWAY_SUBS to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavouriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)