package org.gic;

/**
 * Represents the attributes of each cell
 */
public class Cell {
    private boolean isMine = false;
    private boolean isRevealed = false;
    private int adjacentMines = 0;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    @Override
    public String toString() {
        if (!isRevealed) {
            return "_";
        }
        if (isMine) {
            return " ";
        }
        return adjacentMines == 0 ? "0" : String.valueOf(adjacentMines);
    }
}
