package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {

    CommandHandler commandHandler;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        commandHandler = new CommandHandler();
        System.setOut(new PrintStream(outputStreamCaptor));
        commandHandler.initializeSystem(10);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void handleInput() {
        /*
            [U|u] Pen up
            [D|d] Pen down
            [R|r] Turn right
            [L|l] Turn left
            [M s|m s] Move forward s spaces (s is a non-negative integer)
            [P|p] Print the N by N array and display the indices
            [C|c] Print current position of the pen and whether it is up or down and its facing direction
            [Q|q] Stop the program
            [I n|i n] Initialize the system: The values of the array floor are zeros and the robot is back to [0, 0],
                        pen up and facing north. n size of the array, an integer greater than zero
           
         */

        String value1 = "u";
        String value2 = "U";
        String value3 = "d";
        String value4 = "D";
        String value5 = "r";
        String value6 = "R";
        String value7 = "l";
        String value8 = "L";
        String value9 = "M 2";
        String value10 = "m 2";
        String value11 = "P";
        String value12 = "p";
        String value13 = "C";
        String value14 = "c";
        String value15 = "Q";
        String value16 = "q";
        String value17 = "I 10";
        String value18 = "i 10";
        String value19 = "H";
        String value20 = "h";
        String value21 = "";
        String value22 = "i";
        String value23 = "m";
        String value24 = "i 1";
        String value25 = "menu";
        String value26 = "m 0";
        String value27 = "m 10";

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value11,true);
        assertEquals(table(false), outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value12,true);
        assertEquals(table(false), outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value1,true);
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value2,true);
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value3,true);
        assertEquals("Pen direction going down...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value4,true);
        assertEquals("Pen direction already down", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value5,true);
        assertEquals("Jarvis Turning right...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value6,true);
        assertEquals("Jarvis Turning right...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value7,true);
        assertEquals("Jarvis Turning left...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value8,true);
        assertEquals("Jarvis Turning left...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value9,true);
        assertEquals("Jarvis Moving...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value10,true);
        assertEquals("Jarvis Moving...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        String output = "Position: 0, 4 - Pen: down - Facing: north";
        CommandHandler.handleInput(value13,true);
        assertEquals(output, outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value14,true);
        assertEquals(output, outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        assertTrue(CommandHandler.handleInput(value15,true));

        outputStreamCaptor.reset();
        assertTrue(CommandHandler.handleInput(value16,true));

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value17,true);
        assertEquals("Initializing with size: 10", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value18,true);
        assertEquals("Initializing with size: 10", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value19,true);
        assertTrue(commandHandler.commands.isEmpty());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value20,true);
        assertTrue(commandHandler.commands.isEmpty());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value21,true);
        assertEquals("User selected Nothing", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value22,true);
        assertEquals("Sorry I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value23,true);
        assertEquals("Sorry I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value24,true);
        assertEquals("Sorry I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();

        output = "Enter 'Q' to close program\n"
                 +"Possible commands:\n" +
                "I n: Initialize the system\n" +
                "U: Pen Up\n" +
                "D: Pen Down\n" +
                "R: Turn Right\n" +
                "L: Turn Left\n" +
                "M s: Move forward s spaces\n" +
                "P: Print the table\n" +
                "C: Print current position of the pen\n" +
                "Q: Stop the program";

        CommandHandler.handleInput(value25,true);
        assertEquals(output.replaceAll("\n", "").replaceAll("\r", ""), outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value26,true);
        assertEquals("", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value27,true);
        assertEquals("Can not move " + 10 + " in the " + commandHandler.robot.getDirection() + " direction, the robot will fall off the table", outputStreamCaptor.toString().trim());

    }

    @Test
    void intValueGiven() {
        //gets int value after a letter in a string
        String value1 = "i10";
        String value2 = "i";
        String value3 = "i 5";
        String value4 = "i -5";
        assertAll(() -> assertEquals(10, CommandHandler.intValueGiven(value1)), //test valid number with no space
                () -> assertEquals(-1,commandHandler.intValueGiven(value2)),   //test no number given
                () -> assertEquals(5,commandHandler.intValueGiven(value3)),    //test valid number with space
                () -> assertEquals(-1,commandHandler.intValueGiven(value4)));  //test invalid number
    }

    @Test
    void removeBlankSpace() {
        String value = " I";
        StringBuilder sb = new StringBuilder(value);

        assertEquals("I",commandHandler.removeBlankSpace(sb));
    }

    @Test
    void isNumeric() {
        String num = "10";
        String notNum = "A";
        assertAll(() -> assertTrue(commandHandler.isNumeric(num)),    //test valid number
                () -> assertFalse(commandHandler.isNumeric(notNum))); //test invalid number
    }

    @Test
    void printPosition() {
        String position1 = "Position: " + 0 + ", " + 0 + " - Pen: " + "up" + " - Facing: " + "north";
        String position2 = "Position: " + 0 + ", " + 0 + " - Pen: " + "down" + " - Facing: " + "north";
        String position3 = "Position: " + 0 + ", " + 0 + " - Pen: " + "down" + " - Facing: " + "east";
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(position1,outputStreamCaptor.toString().trim());     //test initial position
        commandHandler.penDown();
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(position2,outputStreamCaptor.toString().trim());    //test position with pen down
        commandHandler.turnRight();
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(position3,outputStreamCaptor.toString().trim());    //test position facing east

    }

    String table(boolean flag){
        //get expected output
        StringBuilder output = new StringBuilder();

        int[][] tableArray;
        //initialize array
        tableArray = new int[10][10];

        //initialize table with 0s
        for (int[] ints : tableArray) {
            Arrays.fill(ints, 0);
        }

        if(flag){
            tableArray[5][5]=1;
        }

        //get initial table
        for (int row = tableArray.length-1; row >= 0; row--) {
            output.append(row + "| ");
            for (int col = 0; col < tableArray[row].length; col++) {
                if (row == 0 && col == 0)
                    output.append("↑ ");
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
    @Test
    void printTable() {

        String output  = table(true);

        outputStreamCaptor.reset();
        Point point = new Point(5,5);
        commandHandler.table.writeTable(point,true);
        commandHandler.printTable();
        assertEquals(output, outputStreamCaptor.toString());
    }

    @Test
    void moveRobot() {
        Point coo1 = commandHandler.robot.getCoordinates();

        coo1.move(coo1.x+1,coo1.y);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coo1.move(coo1.x,coo1.y+1);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coo1.move(coo1.x-1,coo1.y);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coo1.move(coo1.x,coo1.y-1);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());
    }

    @Test
    void turnLeft() {
        commandHandler.turnLeft();
        assertEquals("west", commandHandler.robot.getDirection()); //test left turn if initially facing west
        commandHandler.turnLeft();
        assertEquals("south", commandHandler.robot.getDirection()); //test left turn if initially facing south
        commandHandler.turnLeft();
        assertEquals("east", commandHandler.robot.getDirection()); //test left turn if initially facing east
        commandHandler.turnLeft();
        assertEquals("north", commandHandler.robot.getDirection()); //test left turn if initially facing north
    }

    @Test
    void turnRight() {
        commandHandler.turnRight();
        assertEquals("east", commandHandler.robot.getDirection());  //test right turn if initially facing east
        commandHandler.turnRight();
        assertEquals("south", commandHandler.robot.getDirection());  //test right turn if initially facing south
        commandHandler.turnRight();
        assertEquals("west", commandHandler.robot.getDirection());  //test right turn if initially facing west
        commandHandler.turnRight();
        assertEquals("north", commandHandler.robot.getDirection());  //test right turn if initially facing north

    }

    @Test
    void penDown() {
        //test pen initially up
        commandHandler.penDown();
        assertTrue(commandHandler.robot.getPenState());

        //test pen already down
        outputStreamCaptor.reset();
        commandHandler.penDown();
        assertEquals("Pen direction already down", outputStreamCaptor.toString().trim());
    }

    @Test
    void penUp() {
        //test pen initially up
        outputStreamCaptor.reset();
        commandHandler.penUp();
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        //test pen already up
        commandHandler.robot.setPenState(true);
        commandHandler.penUp();
        assertFalse(commandHandler.robot.getPenState());
    }

    @Test
    void initializeSystem() {
        //test with valid table size
        commandHandler.initializeSystem(5);
        assertTrue(commandHandler.initialized);

        //test with invalid table size
        outputStreamCaptor.reset();
        commandHandler.initializeSystem(1);
        assertEquals("Please choose a size bigger or equal to 2", outputStreamCaptor.toString().trim());
    }



    // Test case 11
    @Test
    void runHistory(){
        commandHandler.commands.add("i10");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("r");
        commandHandler.commands.add("d");
        commandHandler.commands.add("m5");

        commandHandler.runHistory();
        assertTrue(commandHandler.commands.isEmpty());
    }
}