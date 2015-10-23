package me.rikinmarfatia.mintask.models;

/**
 * A model for a task.
 *
 *  @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class Task {
    private String mTitle;
    private boolean mComplete;

    public Task(String title) {
        mTitle = title;
        mComplete = false;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isComplete() {
        return mComplete;
    }

    public void setComplete(boolean complete) {
        mComplete = complete;
    }

    @Override
    public String toString() {
        return mTitle;
    }

}
