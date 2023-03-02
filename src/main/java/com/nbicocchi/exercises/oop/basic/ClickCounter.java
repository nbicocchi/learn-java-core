package com.nbicocchi.exercises.oop.basic;

public class ClickCounter {
    private int value;

    /**
     * Gets the current value of this counter.
     *
     * @return the current value
     */
    public int getValue() {
        return value;
    }

    /**
     * Advances the value of this counter by 1.
     */
    public void click() {
        value += 1;
    }

    /**
     * Undo accidental clicks by decreasing internal counter.
     * Prevents also the counter to go below 0.
     */
    public void undo() {
        value = Math.max(value - 1, 0);
    }

    /**
     * Resets the value of this counter to 0.
     */
    public void reset() {
        value = 0;
    }
}
