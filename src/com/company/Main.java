package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Player player = new Player("Gracz", 'x', true);
        Npc npc = new Npc("NPC");
        Board board = new Board(player, npc);
        board.createMatrix();
        board.setNPC();

        while(!board.isGAME_OVER())
        {
            board.theGame();
        }
    }
}
