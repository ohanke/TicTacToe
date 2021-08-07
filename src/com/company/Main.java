package com.company;

public class Main {
    public static void main(String[] args) throws NullPointerException {

        Player firstPlayer = new Player(true, 'X', true);
        Player secondPlayer = new Player(false, 'o', false);

        Board board = new Board(firstPlayer, secondPlayer);
        board.setGAME_OVER(false);
        board.setFIELDS_TAKEN(0);

        while(!board.isGAME_OVER()){
            if (board.getFIELDS_TAKEN() == (board.getBOARD_SIZE() * board.getBOARD_SIZE())){
                board.setGAME_OVER(true);
            }
            else{
                board.game();
            }
        }

        System.out.println("-------------------- GAME OVER ---------------------");
    }
}