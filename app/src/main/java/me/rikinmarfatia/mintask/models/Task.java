package me.rikinmarfatia.mintask.models;

import me.rikinmarfatia.mintask.R;

/**
 * A model for a task.
 *
 *  @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class Task {
    private String mTitle;
    private int mColor;
    private boolean mComplete;

    public Task(String title) {
        mTitle = title;
        mComplete = false;
        mColor = R.color.white;
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

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    @Override
    public String toString() {
        return mTitle;
    }

}
