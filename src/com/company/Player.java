package com.company;

import java.util.Scanner;

public class Player {
    Scanner scanner = new Scanner(System.in);

    private String NAME;
    private char SIGN;
    private int X;
    private int Y;
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
        setX(scanner.nextInt());
        setY(scanner.nextInt());
    }

    private void setX(int x) {
        this.X = x;
    }

    private void setY(int y) {
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
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
