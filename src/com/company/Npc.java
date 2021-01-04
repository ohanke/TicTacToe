package com.company;

import java.util.Random;

public class Npc {
    private String NAME;
    private char SIGN;
    private int NUMBERS_RANGE;
    private int ROW;
    private int COLUMN;

    public Npc(String NAME) {
        this.NAME = NAME;
    }

    public void generateCoordinates(){
        Random generator = new Random();
        int x = generator.nextInt(getNUMBERS_RANGE());
        int y = generator.nextInt(getNUMBERS_RANGE());
        setROW(x);
        setCOLUMN(y);
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

    private void setROW(int ROW) {
        this.ROW = ROW;
    }

    private void setCOLUMN(int COLUMN) {
        this.COLUMN = COLUMN;
    }

    public int getROW() {
        return ROW;
    }

    public int getCOLUMN() {
        return COLUMN;
    }

    public String getNAME() {
        return NAME;
    }
}
