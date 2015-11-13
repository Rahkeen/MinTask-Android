package me.rikinmarfatia.mintask.models;

import java.util.UUID;

import me.rikinmarfatia.mintask.R;
import me.rikinmarfatia.mintask.util.ColorStrings;

/**
 * A model for a task.
 *
 *  @author Rikin Marfatia (rikinm10@gmail.com)
 */
public class Task {
    private UUID mId;
    private String mTitle;
    private String mColor;
    private boolean mComplete;

    public Task(String title) {
        this(title, UUID.randomUUID());
    }

    public Task(String title, UUID id) {
        mId = id;
        mTitle = title;
        mComplete = false;
        mColor = ColorStrings.WHITE;
    }

    public UUID getId() {
        return mId;
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

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    @Override
    public String toString() {
        return mTitle;
    }

}
