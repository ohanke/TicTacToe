package com.company;

public class Board {

    int TURN = 0;

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

        this.BOARD = new char[BOARD_SIZE][BOARD_SIZE];
        createBoard(BOARD_SIZE, BOARD);
    }

    public void game() {
        boolean victory = false;

        if (HUMAN.isMAKES_MOVE()) {
            humansMove(HUMAN, AI, BOARD);

            if (winningRow(HUMAN.getSIGN(), BOARD) ||
                    winningColumn(HUMAN.getSIGN(), BOARD) ||
                    winningDiagonal(HUMAN.getSIGN(), BOARD)) {
                victory = true;
            }

        } else {
            aiMove(AI, HUMAN, BOARD);

            if (winningRow(AI.getSIGN(), BOARD) ||
                    winningColumn(AI.getSIGN(), BOARD) ||
                    winningDiagonal(AI.getSIGN(), BOARD)) {
                victory = true;
            }
        }

        if (victory){
            System.out.println(winnerMessage(HUMAN));
            setGAME_OVER(true);
        }

        FIELDS_TAKEN++;
        printBoard(BOARD, BOARD_SIZE);
    }

    private void createBoard(int boardSize, char[][] board) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private void humansMove(Player human, Player ai, char[][] board) {
        System.out.println("Podaj dwie współrzędne");
        human.setPOINT();
        if (!(board[human.getPOINT().getX()][human.getPOINT().getY()] == EMPTY_CELL)) {
            unavailableFieldMessage();
            game();
        }

        board[human.getPOINT().getX()][human.getPOINT().getY()] = human.getSIGN();
        human.setMAKES_MOVE(false);
        ai.setMAKES_MOVE(true);
    }

    private void aiMove(Player ai, Player human, char[][] board) {
        ai.setPOINT(findBestMove().getX(), findBestMove().getY());

//        if (!(board[ai.getPOINT().getX()][ai.getPOINT().getY()] == EMPTY_CELL)){
//            unavailableFieldMessage();
//            game();
//        }

        board[AI.getPOINT().getX()][AI.getPOINT().getY()] = AI.getSIGN();
        ai.setMAKES_MOVE(false);
        human.setMAKES_MOVE(true);
    }

    private void unavailableFieldMessage() {
        System.out.println("To pole jest zajęte. Wybierz inne.");
    }

    private String winnerMessage(Player human) {
        return human.isMAKES_MOVE() ? "Komputer wygrał!" : "Gracz wygrał!";
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

    @Override
    public String toString() {
        return BOARD[0][0] + " | " + BOARD[0][1] + " | " + BOARD[0][2] + " | \n" +
                BOARD[1][0] + " | " + BOARD[1][1] + " | " + BOARD[1][2] + " | \n" +
                BOARD[2][0] + " | " + BOARD[2][1] + " | " + BOARD[2][2] + " | \n\n\n";
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
                    int score = minimax(false);
                    board[i][j] = EMPTY_CELL;
                    if (score >= bestScore) {
                        bestScore = score;
                        bestPoint.setX(i);
                        bestPoint.setY(j);
                        System.out.println("Returning best point " + bestPoint.toString());
                        return bestPoint;
                    }
                }
            }
        }
        return bestPoint;
    }

    private int minimax(boolean isMaximizing) {

        TURN++;
        System.out.println("Tura: " + TURN);
        if (TURN >= 150) {
            System.out.println("Tura >= 150");
            return 0;
        }

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
                        printBoard(BOARD, BOARD_SIZE);
                        int score = minimax(false);
                        BOARD[i][j] = EMPTY_CELL;
                        printBoard(BOARD, BOARD_SIZE);
                        if (score >= bestScore) {
                            bestScore = score;
                            System.out.println("Returning best score: " + bestScore);
                            return bestScore;
                        }
                    }
                }
            }
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (BOARD[i][j] == EMPTY_CELL) {
                        BOARD[i][j] = HUMAN.getSIGN();
                        printBoard(BOARD, BOARD_SIZE);
                        int score = minimax(true);
                        BOARD[i][j] = EMPTY_CELL;
                        printBoard(BOARD, BOARD_SIZE);
                        if (score <= bestScore) {
                            bestScore = score;
                            System.out.println("Returning best score: " + bestScore);
                            return bestScore;
                        }
                    }
                }
            }
        }
        return 0;
    }
}