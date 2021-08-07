package com.company;

import java.util.Scanner;

public class Player {
    Scanner scanner = new Scanner(System.in);

    private boolean IS_HUMAN;
    private final char SIGN;
    private boolean MAKES_MOVE;
    private Point POINT;

    public Player(boolean is_human, char sign, boolean makesMove) {
        this.IS_HUMAN = is_human;
        this.SIGN = sign;
        this.MAKES_MOVE = makesMove;
    }

    public char getSIGN() {
        return SIGN;
    }

    public Point getPOINT() {
        return POINT;
    }

    public void setPOINT() {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        this.POINT = new Point(x, y);
    }

    public void setPOINT(int x, int y){
        this.POINT = new Point(x, y);
    }

    public boolean isMAKES_MOVE() {
        return MAKES_MOVE;
    }

    public void setMAKES_MOVE(boolean MAKES_MOVE) {
        this.MAKES_MOVE = MAKES_MOVE;
    }
}