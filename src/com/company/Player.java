package com.company;

import java.util.Scanner;

public class Player {
    Scanner scanner = new Scanner(System.in);

    private String NAME;
    private char SIGN;
    private int ROW;
    private int COLUMN;
    private boolean PLAYERS_TURN;

    public Player(String NAME, char SIGN, boolean PLAYERS_TURN) {
        this.NAME = NAME;
        this.SIGN = SIGN;
        this.PLAYERS_TURN = PLAYERS_TURN;
    }

    public char getSIGN() {
        return SIGN;
    }

    public void setCoordinates(){
        System.out.println("Podaj numer wiersza oraz kolumny: ");
        setROW(scanner.nextInt());
        setCOLUMN(scanner.nextInt());
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

    public void setPLAYERS_TURN(boolean PLAYERS_TURN) {
        this.PLAYERS_TURN = PLAYERS_TURN;
    }

    public boolean getPLAYERS_TURN() {
        return PLAYERS_TURN;
    }

    public String getNAME() {
        return NAME;
    }
}
