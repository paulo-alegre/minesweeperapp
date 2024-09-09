package org.gic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MineSweeperAppTest{

    private MineSweeperApp game;

    @BeforeEach
    void setUp() {
        game = new MineSweeperApp(4, 4, 3);
    }

    @Test
    void testGameInitialization() {
        assertNotNull(game, "Game should be initialized.");
    }

    @Test
    void testPlayerMove() {
        game.getBoard().getCell(1, 1).setMine(false);
        boolean isGameOver = game.getBoard().revealCell(1, 1);
        assertFalse(isGameOver, "The game should continue when a safe cell is revealed.");
    }

    @Test
    void testPlayerHitsMine() {
        game.getBoard().getCell(1, 1).setMine(true);
        boolean isGameOver = game.getBoard().revealCell(1, 1);
        assertTrue(isGameOver, "The game should be over when a mine is hit.");
    }
}
