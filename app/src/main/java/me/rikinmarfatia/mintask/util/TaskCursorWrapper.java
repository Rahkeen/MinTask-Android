package me.rikinmarfatia.mintask.util;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import me.rikinmarfatia.mintask.models.Task;
import me.rikinmarfatia.mintask.util.MinTaskDBSchema.TaskTable;

/**
 * Simple wrapper that allows task population after reading from the db
 *
 * @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        String idString = getString(getColumnIndex(TaskTable.Columns.ID));
        String title = getString(getColumnIndex(TaskTable.Columns.TITLE));
        int complete = getInt(getColumnIndex(TaskTable.Columns.COMPLETE));
        int color = getInt(getColumnIndex(TaskTable.Columns.COLOR));

        Task task = new Task (title, UUID.fromString(idString));
        task.setComplete(complete != 0);
        task.setColor(color);

        return task;
    }
}
