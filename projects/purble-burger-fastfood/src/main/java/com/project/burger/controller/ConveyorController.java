package com.project.burger.controller;

public class ConveyorController {
    private int currentStationIndex;

    public void moveToNextStation() {
        if (currentStationIndex < 4) {
            currentStationIndex++;
        }
    }

    public void moveToPreviousStation() {
        if (currentStationIndex > 0) {
            currentStationIndex--;
        }
    }

    public int getCurrentStationIndex() {
        return currentStationIndex;
    }

    public void reset() {
        currentStationIndex = 0;
    }
}