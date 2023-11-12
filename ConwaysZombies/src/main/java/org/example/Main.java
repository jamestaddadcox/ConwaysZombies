package org.example;

public class Main {
    public static void main(String[] args) {


        Board board = new Board(5, 5);

        // initialize grid

        board.grid[1][1] = Board.Type.living;
        board.grid[1][0] = Board.Type.living;
        board.grid[2][1] = Board.Type.living;
        board.grid[4][4] = Board.Type.living;
        board.grid[2][0] = Board.Type.zombie;

        // run the game

        board.runGame(5);

    }



}