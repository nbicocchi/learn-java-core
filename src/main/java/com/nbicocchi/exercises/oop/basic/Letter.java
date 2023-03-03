package com.nbicocchi.exercises.oop.basic;

import java.util.ArrayList;

/**
 * A class representing a letter written in a standard format.
 * Its methods allow for setting sender, recipient and the lines of the body of the letter.
 * Once all the components have been set, the getText() methods returns the letter in a standard format.
 */
public class Letter {
    String from;
    String to;
    ArrayList<String> lines;

    /**
     * Constructs and initializes a letter with a sender and a recipient
     * @param from the sender
     * @param to the recipient
     */
    public Letter(String from, String to) {
        this.from = from;
        this.to = to;
        this.lines = new ArrayList<>();
    }

    /**
     * Adds a line of text to the body of the letter
     * @param line the line to be added
     */
    public void addLine(String line) {
        lines.add(line);
    }

    /**
     * Returns a representation of the complete letter
     * @return the complete letter
     */
    public String getText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ")
                .append(to)
                .append(":\n\n");
        for (String line : lines) {
            sb.append(line).append("\n");
        }
        sb.append("\n")
                .append("Sincerely,")
                .append("\n")
                .append(from);
        return sb.toString();
    }
}
