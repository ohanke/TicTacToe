package com.company;

import java.util.Arrays;

public class Board {
    private Player HUMAN;
    private Player AI;
    private char[][] BOARD;
    private final int BOARD_SIZE;
    private final char EMPTY_CELL;
    private int FIELDS_TAKEN;
    private boolean GAME_OVER;

    public Board(Player human, Player ai) {
        this.HUMAN = human;
        this.AI = ai;

        BOARD_SIZE = 3;
        EMPTY_CELL = ' ';

        this.BOARD = createBoard(BOARD_SIZE);
    }

    public void gameEngine() {
        if (HUMAN.isMAKES_MOVE()) {
            humansMove(HUMAN, BOARD);
            AI.setMAKES_MOVE(true);
            HUMAN.setMAKES_MOVE(false);

            if (winningRow(HUMAN.getSIGN(), BOARD) ||
                    winningColumn(HUMAN.getSIGN(), BOARD) ||
                    winningDiagonal(HUMAN.getSIGN(), BOARD)) {
                System.out.println(winnerMessage());
                setGAME_OVER(true);
            }

        } else {
            aiMove(AI, BOARD);
            AI.setMAKES_MOVE(false);
            HUMAN.setMAKES_MOVE(true);

            if (winningRow(AI.getSIGN(), BOARD) ||
                    winningColumn(AI.getSIGN(), BOARD) ||
                    winningDiagonal(AI.getSIGN(), BOARD)) {
                System.out.println(winnerMessage());
                setGAME_OVER(true);
            }
        }

        FIELDS_TAKEN++;
        printBoard(BOARD, BOARD_SIZE);
    }

    private char[][] createBoard(int boardSize) {
        char [][] matrix = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                matrix[i][j] = EMPTY_CELL;
            }
        }
        return matrix;
    }

    private void humansMove(Player human, char[][] board) {
        System.out.println("Podaj dwie współrzędne");
        human.setPOINT();
        if (!(board[human.getPOINT().getX()][human.getPOINT().getY()] == EMPTY_CELL)) {
            unavailableFieldMessage();
            gameEngine();
        }

        board[human.getPOINT().getX()][human.getPOINT().getY()] = human.getSIGN();
    }

    private void aiMove(Player ai, char[][] board) {
        ai.setPOINT(findBestMove().getX(), findBestMove().getY());

        board[AI.getPOINT().getX()][AI.getPOINT().getY()] = AI.getSIGN();
    }

    private void unavailableFieldMessage() {
        System.out.println("To pole jest zajęte. Wybierz inne.");
    }

    private String winnerMessage() {
        return HUMAN.isMAKES_MOVE() ? "Komputer wygrał!" : "Gracz wygrał!";
    }

    public boolean isGAME_OVER() {
        return GAME_OVER;
    }

    public void setGAME_OVER(boolean GAME_OVER) {
        this.GAME_OVER = GAME_OVER;
    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    public int getFIELDS_TAKEN() {
        return FIELDS_TAKEN;
    }

    public void setFIELDS_TAKEN(int FIELDS_TAKEN) {
        this.FIELDS_TAKEN = FIELDS_TAKEN;
    }



        private void printBoard(char[][] board, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    private boolean winningRow(char sign, char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == sign)
                    count++;
            }
            if (count == 3)
                return true;
            count = 0;
        }
        return false;
    }

    private boolean winningColumn(char sign, char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == sign)
                    count++;
            }
            if (count == 3)
                return true;
            count = 0;
        }
        return false;
    }

    private boolean winningDiagonal(char sign, char[][] board) {
        return (board[0][0] == sign && board[1][1] == sign && board[2][2] == sign) ||
                (board[0][2] == sign && board[1][1] == sign && board[2][0] == sign);
    }

    private Point findBestMove() {
        int size = BOARD_SIZE;
        char[][] board = BOARD;
        Player ai = AI;

        Point bestPoint = new Point();
        int bestScore = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    board[i][j] = ai.getSIGN();
                    int score = minimax(false, 0);
                    board[i][j] = EMPTY_CELL;
                    if (score > bestScore) {
                        bestScore = score;
                        bestPoint.setX(i);
                        bestPoint.setY(j);
                    }
                }
            }
        }
        return bestPoint;
    }

    private int minimax(boolean isMaximizing, int depth) {

        if (winningRow(AI.getSIGN(), BOARD) || winningColumn(AI.getSIGN(), BOARD) || winningDiagonal(AI.getSIGN(), BOARD))
            return 1;
        if (winningRow(HUMAN.getSIGN(), BOARD) || winningColumn(HUMAN.getSIGN(), BOARD) || winningDiagonal(HUMAN.getSIGN(), BOARD))
            return -1;
        if (FIELDS_TAKEN == 9)
            return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (BOARD[i][j] == EMPTY_CELL) {
                        BOARD[i][j] = AI.getSIGN();
                        int score = minimax(false, depth + 1);
                        BOARD[i][j] = EMPTY_CELL;
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (BOARD[i][j] == EMPTY_CELL) {
                        BOARD[i][j] = HUMAN.getSIGN();
                        int score = minimax(true, depth + 1);
                        BOARD[i][j] = EMPTY_CELL;
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }
}