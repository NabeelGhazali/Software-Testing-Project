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

    //TestCase 1 (Testing all the possible command and the asserting with console output)
    
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
    	String value00 = "P";
        String value000 = "p";
        String value1 = "u";
        String value2 = "U";
        String value3 = "d";
        String value4 = "D";
        String value5 = "r";
        String value6 = "R";
        String value7 = "l";
        String value8 = "L";
        String value9 = "M 2";
        String value10 = "m 3";
        String value11 = "C";
        String value12 = "c";
        String value13 = "Q";
        String value14 = "q";
        String value15 = "I 5";
        String value16 = "i 7";
        String value17 = "";
        String value18 = "i";
        String value19 = "m";
        String value20 = "i 1"; //checking the minimum value of 2 for initialization
        String value21 = "menu";
        String value22 = "m 10";

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value00,true);
        assertEquals(table(false), outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value000,true);
        assertEquals(table(false), outputStreamCaptor.toString());
        
        outputStreamCaptor.reset();
        CommandHandler.handleInput(value15,true);
        assertEquals("Initializing with size: 5", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value16,true);
        assertEquals("Initializing with size: 7", outputStreamCaptor.toString().trim());


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
        String output = "Position: 0, 5 - Pen: down - Facing: north";
        CommandHandler.handleInput(value11,true);
        assertEquals(output, outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value12,true);
        assertEquals(output, outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        assertTrue(CommandHandler.handleInput(value13,true));

        outputStreamCaptor.reset();
        assertTrue(CommandHandler.handleInput(value14,true));

        
        outputStreamCaptor.reset();
        CommandHandler.handleInput(value17,true);
        assertEquals("User selected Nothing", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value18,true);
        assertEquals("Sorry I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value19,true);
        assertEquals("Sorry I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value20,true);
        assertEquals("Sorry I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();

        output = "Enter 'Q' to close program\n"
                 +"All Possible commands:\n" +
                "I n: Initialize the system\n" +
                "U: Pen Up\n" +
                "D: Pen Down\n" +
                "R: Turn Right\n" +
                "L: Turn Left\n" +
                "M s: Move forward s spaces\n" +
                "P: Print the table\n" +
                "C: Print current position of the pen\n" +
                "Q: Stop the program";

        CommandHandler.handleInput(value21,true);
        assertEquals(output.replaceAll("\n", "").replaceAll("\r", ""), outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));

        
        outputStreamCaptor.reset();
        CommandHandler.handleInput(value22,true);
        assertEquals("Can not move " + 10 + " in the " + commandHandler.robot.getDirection() + " direction, the robot will fall off the table", outputStreamCaptor.toString().trim());

    }

//Test case 2 (Checking if the whitespace is removed from the input string) 
    @Test
    void removeBlankSpace() {
        String value = " C";
        StringBuilder sb = new StringBuilder(value);

        assertEquals("C",commandHandler.removeBlankSpace(sb));
    }
    
  //Test case 3 (Checking if after initialize and move integer value is given or not)
    @Test
    void isNumeric() {
        String num = "5";
        String notNum = "M";
        assertAll(() -> assertTrue(commandHandler.isNumeric(num)),    //test valid number
                () -> assertFalse(commandHandler.isNumeric(notNum))); //test invalid number
    }
    
  //Test case 4 (Checking if integer value is collected from the input string is correct or not while initializing or not
    @Test
    void intValueGiven() {
        // finding the integer value from the input string
        String value1 = "i8";
        String value2 = "i";
        String value3 = "i 3";
        String value4 = "i -2";
        assertAll(() -> assertEquals(8, CommandHandler.intValueGiven(value1)), //test valid number with no space
                () -> assertEquals(-1,commandHandler.intValueGiven(value2)),   //test no number given
                () -> assertEquals(3,commandHandler.intValueGiven(value3)),    //test valid number with space
                () -> assertEquals(-1,commandHandler.intValueGiven(value4)));  //test invalid number
    }
    
    
    //Test case 5 (testing the initialization of the robot)
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
    
    // Test case 6 (matching the positions of the pen with pens initial position and 2 other position ) 
    @Test
    void printPosition() {
        String position1 = "Position: " + 0 + ", " + 0 + " - Pen: " + "up" + " - Facing: " + "north";
        String position2 = "Position: " + 0 + ", " + 0 + " - Pen: " + "down" + " - Facing: " + "north";
        String position3 = "Position: " + 0 + ", " + 0 + " - Pen: " + "down" + " - Facing: " + "east";
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(position1,outputStreamCaptor.toString().trim());     //initial position of the pen
        commandHandler.penDown();
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(position2,outputStreamCaptor.toString().trim());    //position of the pen when it is down
        commandHandler.turnRight();
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(position3,outputStreamCaptor.toString().trim());    //Position changed to east and check position

    }
// Test Case 7 (part 1 Checking pen initial up requirement after reset)
    //Part 2 (checking when the pen state is already up) 
    
    @Test
    void penUp() {
        
        outputStreamCaptor.reset();
        commandHandler.penUp();
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        //test pen already up
        commandHandler.robot.setPenState(true);
        commandHandler.penUp();
        assertFalse(commandHandler.robot.getPenState());
    }
 // Test Case 8 (part 1 putting pen down and check after initial up condition)
    //Part 2 (checking when the pen state is already down 
    
    @Test
    void penDown() {
      
        commandHandler.penDown();
        assertTrue(commandHandler.robot.getPenState());

        outputStreamCaptor.reset();
        commandHandler.penDown();
        assertEquals("Pen direction already down", outputStreamCaptor.toString().trim());
    }
    
    //Test case 9 (Checking the left turn of the robot for 4 possible initial conditions (west, south north, east)
    @Test
    void turnLeft() {
        commandHandler.turnLeft();
        assertEquals("west", commandHandler.robot.getDirection()); //turning left if initial is west
        commandHandler.turnLeft();
        assertEquals("south", commandHandler.robot.getDirection()); //turning left if initial is south
        commandHandler.turnLeft();
        assertEquals("east", commandHandler.robot.getDirection()); //turning left if initial is east
        commandHandler.turnLeft();
        assertEquals("north", commandHandler.robot.getDirection()); //turning left if initial is north
    }

  //Test case 10 (Checking the right turn of the robot for 4 possible initial conditions (west, south north, east)
    
    @Test
    void turnRight() {
        commandHandler.turnRight();
        assertEquals("east", commandHandler.robot.getDirection());  //turning right if initial is east
        commandHandler.turnRight();
        assertEquals("south", commandHandler.robot.getDirection());  //turning right if initial is  south
        commandHandler.turnRight();
        assertEquals("west", commandHandler.robot.getDirection());  //turning right if initial is  west
        commandHandler.turnRight();
        assertEquals("north", commandHandler.robot.getDirection());  //turning right if initial is north

    }
    // Test case 11(checking the movement of the robot in the floor)
    @Test
    void moveRobot() {
        Point coordinate = commandHandler.robot.getCoordinates();

        coordinate.move(coordinate.x+1,coordinate.y);
        commandHandler.moveRobot(1);
        assertEquals(coordinate, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coordinate.move(coordinate.x,coordinate.y+1);
        commandHandler.moveRobot(1);
        assertEquals(coordinate, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coordinate.move(coordinate.x-1,coordinate.y);
        commandHandler.moveRobot(1);
        assertEquals(coordinate, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coordinate.move(coordinate.x,coordinate.y-1);
        commandHandler.moveRobot(1);
        assertEquals(coordinate, commandHandler.robot.getCoordinates());
    }

  

 

    

    



   
}