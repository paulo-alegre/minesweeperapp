package org.gic;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GamePrinterTest {

    @Test
    void testPrintBoard() {
        Board board = new Board(4, 4, 3);
        GamePrinter printer = new GamePrinter();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        printer.printBoard(board);

        String expectedOutput =
                "Here is your minefield:\n" +
                "\n "+
                " 1 2 3 4 \n" +
                "A _ _ _ _ \n" +
                "B _ _ _ _ \n" +
                "C _ _ _ _ \n" +
                "D _ _ _ _ \n";
        assertEquals(expectedOutput, outContent.toString(), "The printed board should match the expected format.");
    }
}