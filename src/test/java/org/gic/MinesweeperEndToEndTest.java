package org.gic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MinesweeperEndToEndTest {

    private MineSweeperApp game;
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent = new ByteArrayInputStream(
            ("A1\n").getBytes()
    );

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);

        game = new MineSweeperApp(4, 4, 3);
    }

    @Disabled
    @Test
    void testGameEndToEnd() {
        game.start();

        System.setOut(originalOut);

        String expectedStartOutput =
                "Welcome to Minesweeper!\n" +
                "Enter the size of the grid (e.g. 4 for a 4x4 grid):\n4" +
                "Enter the number of mines to place on the grid (maximum is 35% of the total squares): \n3" +
                "\n" +
                "Here is your minefield:\n" +
                "\n "+
                " 1 2 3 4 \n" +
                "A _ _ _ _ \n" +
                "B _ _ _ _ \n" +
                "C _ _ _ _ \n" +
                "D _ _ _ _ \n" +
                "Select a square to reveal (e.g. A1):\n";

        String actualOutput = outContent.toString();
        System.out.println(actualOutput);

        assertTrue(actualOutput.contains(expectedStartOutput), "Initial game setup output should match the expected.");
    }
}
