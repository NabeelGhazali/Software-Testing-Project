package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    Table table;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        table = new Table(10,10);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void TearDown() {
        System.setOut(standardOut);
    }
    String table(boolean flag){
        //get expected output
        StringBuilder output = new StringBuilder();

        int[][] tableArray = table.getTableArray();
        tableArray[5][5] = 1;

        for (int row = tableArray.length-1; row >= 0; row--) {
            output.append(row + "| ");
            for (int col = 0; col < tableArray[row].length; col++) {
                if (row == 0 && col == 0)
                    if(flag)
                        output.append("↑ ");
                    else
                        output.append("↓ ");
                else if (tableArray[row][col] == 0)
                    output.append("  ");
                else
                    output.append("* ");
            }
            output.append("\n");
        }
        output.append("  ");
        for (int col = 0; col < tableArray.length; col++) {
            output.append("__");
        }
        output.append("\n   ");
        for (int col = 0; col < tableArray.length; col++) {
            output.append(col + " ");
        }

        return output.toString();
    }

   //test case 1 (checking the construction of table is valid or not if negative make 0 then assert zero with output)
    
    @Test
    void testTableConstructor() {
        table = new Table(-3,-3);
        assertEquals(0, table.getHeight());
    }
  //test case 2(checking the coordinates are on the table or not)
    @Test
    void testIsOnTable() {
        //point on table
        Point validPoint = new Point(2,2);
        //point out of bound
        Point invalidPoint = new Point(13,13);
        assertAll(() -> assertTrue(table.isOnTable(validPoint)),        // valid point
                () -> assertFalse(table.isOnTable(invalidPoint)));      // invalid point
    }
  
    //test case 3 (checking the table array with initiate value)
    @Test
    void testGetTableArray() {
        //get expected array
        int[][] tableArray = new int[10][10];

        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                tableArray[row][col] = 0;
            }
        }

        assertTrue(Arrays.deepEquals(tableArray, table.getTableArray()));
    }

    
}