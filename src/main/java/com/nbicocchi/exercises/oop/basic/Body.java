package com.nbicocchi.exercises.oop.basic;

/**
 * Implement a Body class representing a two-dimensional body (x, y) capable of moving with a certain speed (vx, vy).
 * The class implements three constructors:
 * Body() Setting x = y = vx = vy = 0
 * Body(x, y) Setting x, y as specified and vx = vy = 0
 * Body(x, y, vx, vy) Setting x, y, vx, vy as specified
 *
 * The class have also to implement the following methods:
 * getters and setters for x, y, vx, vy
 * progress(t) updating x, y considering a constant movement at velocity vx, vy for t seconds
 * toString() printing x, y, vx, vy
 *
 * @author Nicola Bicocchi
 */
public class Body {
    double x;
    double y;
    double vX;
    double vY;

    public Body() {
        this.x = 0;
        this.y = 0;
        this.vX = 0;
        this.vY = 0;
    }

    public Body(double x, double y) {
        this.x = x;
        this.y = y;
        this.vX = 0;
        this.vY = 0;
    }

    public Body(double x, double y, double vX, double vY) {
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
    }

    public void progress(double t) {
        x += vX * t;
        y += vY * t;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVX() {
        return vX;
    }

    public void setVX(double vX) {
        this.vX = vX;
    }

    public double getVY() {
        return vY;
    }

    public void setVY(double vY) {
        this.vY = vY;
    }

    @Override
    public String toString() {
        return "Body{" + "x=" + x + ", y=" + y + ", vX=" + vX + ", vY=" + vY + '}';
    }
}
