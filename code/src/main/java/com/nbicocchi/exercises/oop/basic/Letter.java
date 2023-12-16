package com.nbicocchi.exercises.oop.basic;

/**
 * A letter written in a standard format.
 * Its methods allow for setting sender, recipient and the lines of the body of the letter.
 * Once all the components have been set, the getText() methods returns the letter in a standard format.
 */
public class Letter {
    String from;
    String to;
    StringBuilder lines;

    /**
     * Constructs and initializes a letter with a sender and a recipient
     * @param from the sender
     * @param to the recipient
     */
    public Letter(String from, String to) {
        this.from = from;
        this.to = to;
        this.lines = new StringBuilder();
    }

    /**
     * Adds a line of text to the body of the letter
     * @param line the line to be added
     */
    public void addLine(String line) {
        lines.append(line).append('\n');
    }

    /**
     * Returns a representation of the complete letter
     * @return the complete letter
     */
    public String getText() {
        return "Dear " + to + ":\n\n" + lines + "\n" + "Sincerely,\n\n" + from;
    }
}
