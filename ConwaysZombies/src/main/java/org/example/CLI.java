package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CLI {
    public CLI(){

    }
    Scanner myScanner = new Scanner(System.in);

    int gridSize;
    int generations;
    Board.Type[][] grid;


    public void runCLI() {
        while (true) {
            System.out.println("Please enter the size of the board as a positive integer:");
            try {
                int userInput = parseAnswerAsInt(myScanner.nextLine());
                if (userInput <= 0) {
                    throw new IllegalArgumentException("Error: Board size must be a positive integer.");
                }
                gridSize = userInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Please enter the number of generations as a positive integer:");
            try {
                int userInput = parseAnswerAsInt(myScanner.nextLine());
                if (userInput <= 0) {
                    throw new IllegalArgumentException("Error: Number of generations must be a positive integer.");
                }
                generations = userInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Board board = new Board(gridSize, gridSize);

        while (true) {
            System.out.println("Please enter the coordinates for a living cell in the format \"x, y\"; or enter C to continue.");
            try {
                String answer = myScanner.nextLine();
                if (answer.equalsIgnoreCase("C")) {
                    break;
                } else {
                    int[] coordinates = parseAnswerAsCoordinate(answer);
                    if (coordinates[0] >= gridSize || coordinates[1] >= gridSize) {
                        throw new ArrayIndexOutOfBoundsException("Error: Point coordinates must occur within a 0-indexed " + gridSize + " by " + gridSize + " grid.");
                    }
                    board.grid[coordinates[0]][coordinates[1]] = Board.Type.living;
                    break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Error: Invalid input format. Please enter coordinates in the format \"x, y\".");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }





    }

    public static int parseAnswerAsInt(String userInput) {
        return Integer.parseInt(userInput);
    }

    public static int[] parseAnswerAsCoordinate(String userInput) {
        int[] coordinates = new int[2];
        String[] input = userInput.split(", ");
        coordinates[0] = Integer.parseInt(input[0]);
        coordinates[1] = Integer.parseInt(input[1]);
        return coordinates;
    }


}
