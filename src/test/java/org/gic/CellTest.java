package org.gic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    void testCellInitialization() {
        Cell cell = new Cell();
        assertFalse(cell.isRevealed(), "Newly created cell should not be revealed.");
        assertFalse(cell.isMine(), "Newly created cell should not be a mine.");
    }

    @Test
    void testSetMine() {
        Cell cell = new Cell();
        cell.setMine(true);
        assertTrue(cell.isMine(), "Cell should be a mine after setting it as one.");
    }

    @Test
    void testRevealCell() {
        Cell cell = new Cell();
        cell.setRevealed(true);
        assertTrue(cell.isRevealed(), "Cell should be revealed after calling reveal().");
    }

    @Test
    void testSetAdjacentMinesCount() {
        Cell cell = new Cell();
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines(), "Adjacent mines count should be 3.");
    }
}
