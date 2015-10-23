package me.rikinmarfatia.mintask.models;

import android.content.Context;

import java.util.ArrayList;

/**
 * Singleton to act as storage for Task objects.
 *
 * @author Rikin Marfataia (rikinm10@gmail.com)
 */
public class AllTasks {

    private ArrayList<Task> mTasks;
    private Context mAppContext;
    private static AllTasks sAllTasks;

    private AllTasks(Context c) {
        mTasks = new ArrayList<>();
        mAppContext = c;

        // TODO: Remove this later, this is just for dummy data.
        for(int i = 1; i <= 5; i++) {
            Task task = new Task("Task " + i);
            mTasks.add(task);
        }
    }

    public static AllTasks getInstance(Context c) {
        if(sAllTasks == null) {
            sAllTasks = new AllTasks(c);
        }

        return sAllTasks;
    }

    public ArrayList<Task> getTasks() {
        return mTasks;
    }
}
