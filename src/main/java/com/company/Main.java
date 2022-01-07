package com.company;

public class Main {
    public static void main(String[] args) throws NullPointerException {

        Player firstPlayer = new Player(true, 'x', false);
        Player secondPlayer = new Player(false, 'o', true);

        Board board = new Board(firstPlayer, secondPlayer);
        board.setGAME_OVER(false);
        board.setFIELDS_TAKEN(0);

        while(!board.isGAME_OVER()){
            if (board.getFIELDS_TAKEN() == (board.getBOARD_SIZE() * board.getBOARD_SIZE())){
                board.setGAME_OVER(true);
            }
            else{
                board.gameEngine();
            }
        }

        System.out.println("-------------------- GAME OVER ---------------------");
    }
}