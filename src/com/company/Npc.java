package com.company;

import java.util.Random;

public class Npc {
    private String NAME;
    private char SIGN;
    private int NUMBERS_RANGE;
    private int X;
    private int Y;

    public Npc(String NAME) {
        this.NAME = NAME;
    }

    public void generateCoordinates(){
        Random generator = new Random();
        int x = generator.nextInt(getNUMBERS_RANGE());
        int y = generator.nextInt(getNUMBERS_RANGE());
        setX(x);
        setY(y);
    }

    public char getSIGN() {
        return SIGN;
    }

    public void setSIGN(char SIGN) {
        this.SIGN = SIGN;
    }

    public void setNUMBERS_RANGE(int NUMBERS_RANGE) {
        this.NUMBERS_RANGE = NUMBERS_RANGE;
    }

    public int getNUMBERS_RANGE() {
        return NUMBERS_RANGE;
    }

    private void setX(int x) {
        X = x;
    }

    private void setY(int y) {
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public String getNAME() {
        return NAME;
    }
}
