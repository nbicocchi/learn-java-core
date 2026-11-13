package com.project.burger.model;

public enum BreadShape {
    CLASSIC("Classic"),
    BAGUETTE("Baguette"),
    BAGEL("Bagel");

    private final String displayName;

    BreadShape(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the bread shape.
     *
     * @return the display name
     */
    @SuppressWarnings("unused")
    public String getDisplayName() {
        return displayName;
    }
}
