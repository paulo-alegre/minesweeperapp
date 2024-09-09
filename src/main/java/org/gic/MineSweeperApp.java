package org.gic;

import java.util.HashMap;
import java.util.Scanner;

/**
 *  Main class executor of the game
 */
public class MineSweeperApp
{
    private Board board;
    private GamePrinter printer;
    private boolean isGameOver = false;
    private final HashMap<Character, Integer> rowMap;

    /**
     * Initialize the game the setup with number of grids and mines
     * @param rows
     * @param cols
     * @param mines
     */
    public MineSweeperApp(int rows, int cols, int mines) {
        board = new Board(rows, cols, mines);
        printer = new GamePrinter();
        rowMap = createRowMap(rows);
    }

    /**
     * Create a row map of letters
     * @param numRows
     * @return
     */
    private HashMap<Character, Integer> createRowMap(int numRows) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < numRows; i++) {
            map.put((char) ('A' + i), i);
        }
        return map;
    }

    /**
     * Method to start the game and to start all over the game
     * if the player has lost or won.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            printer.printBoard(board);
            System.out.println(" ");
            System.out.print("Select a square to reveal (e.g. A1): ");

            char[] rowLetter = scanner.next().toCharArray();

            int col = Integer.parseInt(String.valueOf(rowLetter[1]));
            Integer row = rowMap.get(Character.toUpperCase(rowLetter[0]));

            isGameOver = board.revealCell(row, col-1);

            if (isGameOver) {
                if (board.hasLost()) {
                    System.out.println("Oh no, you detonated a mine! Game over.");
                } else {
                    System.out.println("Congratulations, you have won the game!");
                }
                System.out.println("Press any key to play again...");
                scanner.next();
                restartGame();
            }
        }

        scanner.close();
    }

    /**
     * Restart the game and ask for input of the grid size and mines
     */
    private void restartGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int grid = scanner.nextInt();
        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
        System.out.println(" ");
        int mines = scanner.nextInt();

        this.board = new Board(grid, grid, mines);
        this.printer = new GamePrinter();
        this.isGameOver = false;
        this.start();
    }

    /**
     * Main method to execute the game
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int grid = scanner.nextInt();
        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
        int mines = scanner.nextInt();
        System.out.println(" ");

        MineSweeperApp game = new MineSweeperApp(grid, grid, mines);
        game.start();
    }

    public Board getBoard() {
        return this.board;
    }
}
