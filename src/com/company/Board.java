package com.company;

public class Board
{
    static int TAKEN_FIELDS = 0;

    public final int BOARD_SIZE;
    private char[][] MATRIX;
    private Player PLAYER;
    private Npc NPC;
    private final char EMPTY_PLACE;
    private boolean GAME_OVER;
    //private int DIFFICULTY;

    public Board(Player PLAYER, Npc NPC) {
        this.BOARD_SIZE = 3;
        MATRIX = new char[BOARD_SIZE][BOARD_SIZE];
        this.PLAYER = PLAYER;
        this.NPC = NPC;
        this.EMPTY_PLACE = ' ';
        this.GAME_OVER = false;
    }

    public void theGame() throws InterruptedException {
        boolean isPlayersTurn = PLAYER.getPLAYERS_TURN();

        if(isPlayersTurn){
            printBoard(isPlayersTurn);
            playersMove();
            if(hasWon(isPlayersTurn)){
                setGAME_OVER(true);
                System.out.println(printWinnerMessage(isPlayersTurn));
            }
            PLAYER.setPLAYERS_TURN(false);
        }
        else{
            printBoard(isPlayersTurn);
            npcMove();
            if(hasWon(isPlayersTurn)){
                setGAME_OVER(true);
                System.out.println(printWinnerMessage(isPlayersTurn));
            }
            PLAYER.setPLAYERS_TURN(true);
        }
        if(allFieldsTaken()) {
            System.out.println("Koniec gry. Wszystkie miejsca na planszy wykorzystane.");
            setGAME_OVER(true);
        }

    }

    private boolean allFieldsTaken(){
        return TAKEN_FIELDS >= 9;
    }

    private boolean hasWon(boolean isPlayersTurn){
        char sign;
        int rowNumber;
        int columnNumber;
        if (isPlayersTurn){
            sign = PLAYER.getSIGN();
            rowNumber = PLAYER.getROW();
            columnNumber = PLAYER.getCOLUMN();
        }
        else{
            sign = NPC.getSIGN();
            rowNumber = NPC.getROW();
            columnNumber = NPC.getCOLUMN();
        }
        if (hasThreeInRow(sign, rowNumber))
            return true;
        else if (hatThreeInColumn(sign, columnNumber))
            return true;
        else
            return hasThreeInDiagonal(sign);

    }

    private boolean hasThreeInRow(char sign, int rowNumber){
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++){
            if(MATRIX[rowNumber][i] == sign)
                count++;
        }
        return count == 3;
    }
    private boolean hatThreeInColumn(char sign, int columnNumber){
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if(MATRIX[i][columnNumber] == sign)
                count++;
        }
        return count == 3;
    }
    private boolean hasThreeInDiagonal(char sign){
        return (MATRIX[0][0] == sign && MATRIX[1][1] == sign && MATRIX[2][2] == sign) ||
                (MATRIX[0][2] == sign && MATRIX[1][1] == sign && MATRIX[2][0] == sign);
    }

    private void playersMove(){
        PLAYER.setCoordinates();
        char sign = PLAYER.getSIGN();
        int x = PLAYER.getROW();
        int y = PLAYER.getCOLUMN();

        if(canPlaceSign(x, y)) {
            MATRIX[x][y] = sign;
            TAKEN_FIELDS++;
        }
        else{
            System.out.println("To miejsce jest zajęte! Wybierz inne");
            playersMove();
        }
    }

    private void npcMove(){
        NPC.generateCoordinates();
        char sign = NPC.getSIGN();
        int x = NPC.getROW();
        int y = NPC.getCOLUMN();

        if(canPlaceSign(x, y)){
            MATRIX[x][y] = sign;
            TAKEN_FIELDS++;
        }
        else
            npcMove();
    }

    private boolean canPlaceSign(int x, int y)
    {
        return MATRIX[x][y] == EMPTY_PLACE;
    }

    private String printWinnerMessage(boolean isPlayersTurn){
        return isPlayersTurn ? PLAYER.getNAME() + " wygrywa!" : NPC.getNAME() + " wygrywa!";
    }

    public void printBoard(boolean isPlayersTurn) throws InterruptedException {
        if(TAKEN_FIELDS > 0) {
            if(isPlayersTurn)
                Thread.sleep(2500);
        }

        System.out.println("   0    1    2");
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            System.out.print(i + "| ");
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                System.out.print(MATRIX[i][j] + "  | ");
            }
            System.out.println("");
        }
    }

    public void createMatrix(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                MATRIX[i][j] = EMPTY_PLACE;
            }
        }
    }

    public void setNPC(){
        NPC.setNUMBERS_RANGE(getBOARD_SIZE());
        NPC.setSIGN(findAvalibleSign());
    }

    private int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    private char findAvalibleSign(){
        if(PLAYER.getSIGN() == 'x')
            return 'o';
        return 'x';
    }

    public boolean isGAME_OVER() {
        return GAME_OVER;
    }

    private void setGAME_OVER(boolean GAME_OVER) {
        this.GAME_OVER = GAME_OVER;
    }
}
