package org.gic;

/**
 * Prints the grid in the console
 */
public class GamePrinter {

    /**
     * Prints the minesweeper board and updates the place of mines in the console.
     * @param board
     */
    public void printBoard(Board board) {
        int rows = board.getRows();
        int cols = board.getCols();

        System.out.println(board.hasStarted() ? "Here is your updated minefield:" : "Here is your minefield:");
        System.out.println("");
        System.out.print("  ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < cols; j++) {
                System.out.print(board.getCell(i, j).toString() + " ");
            }
            System.out.println();
        }
    }
}

