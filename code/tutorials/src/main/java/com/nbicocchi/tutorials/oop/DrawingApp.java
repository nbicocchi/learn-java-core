package com.nbicocchi.tutorials.oop;

public class DrawingApp {
    public static void main(String[] args) {
        Curve curve = new Curve();
        DrawingTool[] drawingTools = new DrawingTool[]{new Pencil(), new Brush()};
        for (DrawingTool tool : drawingTools) {
            System.out.println(tool.draw(curve));
        }
    }
}
