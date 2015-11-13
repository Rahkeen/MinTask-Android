package me.rikinmarfatia.mintask.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.rikinmarfatia.mintask.util.MinTaskDBSchema.TaskTable;

/**
 * Utility for opening / creating / updating local SQLite db
 *
 * @author Rikin (rikinm10@gmail.com)
 */
public class MinTaskOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "mintask.db";

    public MinTaskOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TaskTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    TaskTable.Columns.ID + ", " +
                    TaskTable.Columns.TITLE + ", " +
                    TaskTable.Columns.COMPLETE + ", " +
                    TaskTable.Columns.COLOR + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
