package org.gic;


import java.util.Random;

/**
 * Controls the of the game and status of it
 */
public class Board {
    private final Cell[][] grid;
    private final int rows;
    private final int cols;
    private final int mines;
    private boolean hasLost = false;

    private boolean hasStarted = false;

    public Board(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        grid = new Cell[rows][cols];
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
    }

    /**
     *  Initialize the board with unrevealed cells
     */
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }


    /**
     *  Randomly place mines on the board
     */
    private void placeMines() {
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                placedMines++;
            }
        }
    }


    /**
     *  Calculate the number of adjacent mines for each cell
     */
    private void calculateAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isMine()) {
                    grid[i][j].setAdjacentMines(countAdjacentMines(i, j));
                }
            }
        }
    }

    /**
     * Count the number of mines adjacent to the given cell
     *
     * @param row
     * @param col
     * @return the number adjacent of the cell.
     */
    private int countAdjacentMines(int row, int col) {
        int mineCount = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j].isMine()) {
                    mineCount++;
                }
            }
        }

        return mineCount;
    }

    /**
     * Reveal the cell at the given row and column
     * @param row
     * @param col
     * @return the status of the cell
     */
    public boolean revealCell(int row, int col) {
        hasStarted = true;
        if (grid[row][col].isRevealed()) {
            return false;
        }

        grid[row][col].setRevealed(true);

        if (grid[row][col].isMine()) {
            hasLost = true;
            return true;
        }

        if (grid[row][col].getAdjacentMines() == 0) {
            revealCellsNearby(row, col);
        }

        System.out.println("This square contains " + countAdjacentMines(row,col) +" adjacent mines. ");
        System.out.println("  ");
        return checkIfPlayerHasWon();
    }

    /**
     * Reveal all neighboring cells if there are no adjacent mines
     * @param row
     * @param col
     */
    private void revealCellsNearby(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && !grid[i][j].isRevealed() && !grid[i][j].isMine()) {
                    grid[i][j].setRevealed(true);
                    if (grid[i][j].getAdjacentMines() == 0) {
                        revealCellsNearby(i, j);
                    }
                }
            }
        }
    }

    /**
     * Check if the player has won (all non-mine cells revealed)
     * @return the status of the game
     */
    private boolean checkIfPlayerHasWon() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isMine() && !grid[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if the player has lost
     * @return
     */
    public boolean hasLost() {
        return hasLost;
    }

    /**
     * Get the cell at the specified row and column
     * @param row
     * @param col
     * @return the grid
     */
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Get the rows
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     * Return the columns
     * @return
     */
    public int getCols() {
        return cols;
    }

    /**
     * Check if the game has started
     * @return
     */
    public boolean hasStarted() {
        return hasStarted;
    }
}

