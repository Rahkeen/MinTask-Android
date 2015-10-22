package me.rikinmarfatia.mintask.models;

/**
 * A model for a task. Contains basic information like title and details.
 *
 *  @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class Task {
    private String mTitle;
    private boolean complete;

    public Task(String title) {
        mTitle = title;
        complete = false;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void complete() {
        complete = !complete;
    }
}
