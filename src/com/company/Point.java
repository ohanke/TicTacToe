package com.company;

public class Point {
    private int X;
    private int Y;

    public Point() {
    }

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public void setX(int x) { X = x; }

    public void setY(int y) { Y = y; }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public String toString() {
        return ("[ " + X + ", " + Y + " ]");
    }
}
