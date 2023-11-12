package org.example;

/**
 * the Board class contains the grid for Conway's Game and the game's primary logic
 */
public class Board {

    /**
     * an enum used to represent the state, "living" or "zombie," of each point on the board (empty cells are considered "dead")
     */
    public enum Type {
        living,
        zombie,
        dead
    }

    /**
     * the number of columns on the board
     */
    int cols;
    /**
     * the number of rows on the board
     */
    int rows;
    /**
     * a 2D array of Type enums representing the current board. Each space is living, zombie, or empty (dead)
     */
    Type[][] grid;

    public Board(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        grid = new Type[cols][rows];
    }

    /**
     * Runs Conway's Game on the present board for a specified number of generations
     * @param generations the number of generations for which the game should run
     */
    public void runGame(int generations) {
        for (int generation = 0; generation < generations; generation++) {
            System.out.println("Generation " + (generation + 1) + ":");
            printGrid(grid);
            grid = nextGeneration(grid);
        }
    }

    /**
     * function to create a grid representing the game's next generation
     * @param grid the current grid
     * @return the grid representing the next generation, taking into account any zombie attacks
     */
    public Type[][] nextGeneration(Type[][] grid) {
        int cols = grid.length;
        int rows = grid[0].length;
        Type[][] newGrid = new Type[cols][rows];
        Type[][] gridPostZombieAttack = new Type[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int liveNeighbors = countLiveNeighbors(grid, i, j);

                if (grid[i][j] == Type.zombie) {
                    if (!(liveNeighbors >= 3)) {
                        newGrid[i][j] = Type.zombie;
                    }
                } else if (grid[i][j] == Type.living) {
                    if (!(liveNeighbors < 2 || liveNeighbors > 3)) {
                        newGrid[i][j] = Type.living;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        newGrid[i][j] = Type.living;
                    }
                }
            }
        }
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (newGrid[i][j] == Type.living && checkZombieNeighbor(newGrid, i, j)) {
                    gridPostZombieAttack[i][j] = Type.dead;
                } else if (newGrid[i][j] == Type.living) {
                    gridPostZombieAttack[i][j] = Type.living;
                } else if (newGrid[i][j] == Type.zombie) {
                    gridPostZombieAttack[i][j] = Type.zombie;
                }
            }
        }

        return gridPostZombieAttack;
    }

    // count live neighbors of a point

    /**
     * counts a point's living neighbors
     * @param grid the current grid
     * @param x point's x-coordinate
     * @param y point's y-coordinate
     * @return an int of the point's living neighbors
     */
    private int countLiveNeighbors(Type[][] grid, int x, int y) {
        int count = 0;
        int cols = grid.length;
        int rows = grid[0].length;


        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;

                if (neighborX >= 0 && neighborX < cols && neighborY >= 0 && neighborY < rows) {
                    if (!(i == 0 && j == 0) && grid[neighborX][neighborY] == Type.living) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * checks whether a given point has a zombie neighbor
     * @param grid the current grid
     * @param x the point's x-coordinate
     * @param y the point's y-coordinate
     * @return true if point neighbors a zombie
     */
    private boolean checkZombieNeighbor(Type[][] grid, int x, int y) {
        int cols = grid.length;
        int rows = grid[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;

                if (neighborX >= 0 && neighborX < cols && neighborY >= 0 && neighborY < rows) {
                    if (!(i == 0 && j == 0) && grid[neighborX][neighborY] == Type.zombie) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * prints a representation of the current version of the grid
     * @param grid the current grid
     */
    private void printGrid(Type[][] grid) {
        int cols = grid.length;
        int rows = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[j][i] == Type.living) {
                    System.out.print(" * ");
                } else if (grid[j][i] == Type.zombie) {
                    System.out.print(" z ");
                } else {
                    System.out.print(" . ");
                }

//                System.out.print(grid[j][i] == Type.living ? " * " : " . ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
