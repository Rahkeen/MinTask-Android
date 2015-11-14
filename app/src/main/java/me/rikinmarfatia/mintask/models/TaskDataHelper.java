package me.rikinmarfatia.mintask.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.rikinmarfatia.mintask.util.MinTaskDBSchema.TaskTable;
import me.rikinmarfatia.mintask.util.MinTaskOpenHelper;
import me.rikinmarfatia.mintask.util.TaskCursorWrapper;

/**
 * Singleton to act as storage for Task objects.
 *
 * @author Rikin Marfataia (rikinm10@gmail.com)
 */
public class TaskDataHelper {

    private Context mAppContext;
    private SQLiteDatabase mDatabase;
    private static TaskDataHelper sTaskDataHelper;

    private TaskDataHelper(Context c) {
        mAppContext = c.getApplicationContext();
        mDatabase = new MinTaskOpenHelper(mAppContext).getWritableDatabase();

    }

    public static TaskDataHelper getInstance(Context c) {
        if(sTaskDataHelper == null) {
            sTaskDataHelper = new TaskDataHelper(c);
        }

        return sTaskDataHelper;
    }

    public List<Task> getTasks() {
        TaskCursorWrapper cursor = queryTasks(null, null);
        List<Task> tasks = new ArrayList<>();

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tasks;
    }

    private static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskTable.Columns.ID, task.getId().toString());
        values.put(TaskTable.Columns.TITLE, task.getTitle());
        values.put(TaskTable.Columns.COMPLETE, task.isComplete() ? 1 : 0);
        values.put(TaskTable.Columns.COLOR, task.getColor());

        return values;
    }

    public void addTask(Task t) {
        ContentValues values = getContentValues(t);
        mDatabase.insert(TaskTable.NAME, null, values);
    }

    public Task getTask(UUID id) {
        TaskCursorWrapper cursor = queryTasks(
                TaskTable.Columns.ID + " = ? ",
                new String[]{id.toString()}
        );

        try {
            if(cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTask();

        } finally {
            cursor.close();
        }
    }

    public void deleteCompletedTasks() {
        mDatabase.delete(TaskTable.NAME, TaskTable.Columns.COMPLETE
        + " = " + 1, null);
    }

    public void updateTask(Task t) {
        String idString = t.getId().toString();
        ContentValues values = getContentValues(t);

        mDatabase.update(TaskTable.NAME, values,
                TaskTable.Columns.ID + " = ?", new String[]{idString});
    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new TaskCursorWrapper(cursor);
    }
}
