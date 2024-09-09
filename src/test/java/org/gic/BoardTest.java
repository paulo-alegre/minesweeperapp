package org.gic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(4, 4, 3);
    }

    @Test
    void testBoardInitialization() {
        assertEquals(4, board.getRows(), "Board should have 4 rows.");
        assertEquals(4, board.getCols(), "Board should have 4 columns.");
    }

    @Test
    void testMinePlacement() {
        int mineCount = 0;
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (board.getCell(row, col).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(3, mineCount, "Board should have 3 mines.");
    }

    @Test
    void testRevealCell() {
        board.getCell(1,1).setMine(false);
        boolean isGameOver = board.revealCell(1, 1);
        assertFalse(isGameOver, "The game should not be over when revealing a non-mine cell.");
    }

    @Test
    void testRevealCellWithMine() {
        board.getCell(2,2).setMine(true);
        boolean isGameOver = board.revealCell(2, 2);
        assertTrue(isGameOver, "The game should be over when a mine is revealed.");
    }
}
