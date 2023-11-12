package org.example;

public class Board {
    public enum Type {
        living,
        zombie,
        dead
    }

    int cols;
    int rows;
    Type[][] grid;

    public Board(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        grid = new Type[cols][rows];
    }

    public void runGame(int generations) {
        for (int generation = 0; generation < generations; generation++) {
            System.out.println("Generation " + (generation + 1) + ":");
            printGrid(grid);
            grid = nextGeneration(grid);
        }
    }

    public Type[][] nextGeneration(Type[][] grid) {
        int cols = grid.length;
        int rows = grid[0].length;
        Type[][] newGrid = new Type[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int liveNeighbors = countLiveNeighbors(grid, i, j);

                if (grid[i][j] == Type.living) {
                    if (!(liveNeighbors < 2 || liveNeighbors > 3)) {
                        newGrid[i][j] = Type.living;
                    }
//                } else if (grid[i][j] == Type.zombie) {
//                    if (liveNeighbors >= 3) {
//                        newGrid[i][j] = Type.dead;
//                    }
                } else {
                    if (liveNeighbors == 3) {
                        newGrid[i][j] = Type.living;
                    }
                }
            }
        }
        return newGrid;
    }

    // count live neighbors of a point

    private int countLiveNeighbors(Type[][] grid, int x, int y) {
        int count = 0;
        int cols = grid.length;
        int rows = grid[0].length;


        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;

                // check if neighbor is within the bounds of the grid

                if (neighborX >= 0 && neighborX < cols && neighborY >= 0 && neighborY < rows) {
                    if (grid[neighborX][neighborY] == Type.living) {
                        count++;
                    }
                }
            }

            // if the center cell is alive, subtract its value from the final count
            if (grid[x][y] == Type.living) {
                count--;
            }

        }
        return count;

    }

    private void printGrid(Type[][] grid) {
        int cols = grid.length;
        int rows = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[j][i] == Type.living ? " * " : " . ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
