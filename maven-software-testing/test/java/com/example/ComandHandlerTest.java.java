package java.com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Singleton.CommandHandler;

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
        String value9 = "M 3";
        String value10 = "m 5";
        String value13 = "C";
        String value14 = "c";
        

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value00,true);
        assertEquals(table(false), outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value000,true);
        assertEquals(table(false), outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(vauel1,true);
        assertEquals("Pen already up", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value2,true);
        assertEquals("Pen already up", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value3,true);
        assertEquals("Pen going down...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value4,true);
        assertEquals("Pen already down", outputStreamCaptor.toString().trim());

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
        assertEquals("Jarvis is Moving...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value10,true);
        assertEquals("Jarvis is Moving...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        String output = "Position: 0, 8 - Pen: down - Facing: north";
        CommandHandler.handleInput(value13,true);
        assertEquals(output, outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(value14,true);
        assertEquals(output, outputStreamCaptor.toString().trim());


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
    }

    @Test
    void intCommand() {
        // gets int value after a letter in a string
        String val = "i10";
        String val1 = "i";
        String val2 = "i 5";
        String val3 = "i -5";
        assertAll(() -> assertEquals(10, CommandHandler.intCommand(val)), // test valid number with no space
                () -> assertEquals(-1, commandHandler.intCommand(val1)), // test no number given
                () -> assertEquals(5, commandHandler.intCommand(val2)), // test valid number with space
                () -> assertEquals(-1, commandHandler.intCommand(val3))); // test invalid number
    }

    @Test
    void isNumeric() {
        String numb = "10";
        String notNumb = "A";
        assertAll(() -> assertTrue(commandHandler.isNumeric(numb)), // test valid number
                () -> assertFalse(commandHandler.isNumeric(notNumb))); // test invalid number
    }

    @Test
    void printTable() {

        String output = table(true);

        outputStreamCaptor.reset();
        Point point = new Point(5, 5);
        commandHandler.table.writeTable(point, true);
        commandHandler.printTable();
        assertEquals(output, outputStreamCaptor.toString());
    }

    @Test
    void turnLeft() {
        commandHandler.turnLeft();
        assertEquals("west", commandHandler.robot.getDirection());
        // Checking left turn if initially Jarvis is facing west
        commandHandler.turnLeft();
        assertEquals("east", commandHandler.robot.getDirection());
        // Checking left turn if initially Jarvis is facing east
        commandHandler.turnLeft();
        assertEquals("south", commandHandler.robot.getDirection());
        // Checking left turn if initially Jarvis is facing south
        commandHandler.turnLeft();
        assertEquals("north", commandHandler.robot.getDirection());
        // Checking left turn if initially Jarvis is facing north
    }

    @Test
    void turnRight() {
        commandHandler.turnRight();
        assertEquals("east", commandHandler.robot.getDirection());  
        //Checking right turn if initially Jarvis is facing  east
        commandHandler.turnRight();
        assertEquals("west", commandHandler.robot.getDirection());  
        //Checking right turn if initially Jarvis is facing  west
        commandHandler.turnRight();
        assertEquals("south", commandHandler.robot.getDirection()); 
        ///Checking right turn if initially Jarvis is facing  south
        commandHandler.turnRight();
        assertEquals("north", commandHandler.robot.getDirection());  
        //Checking right turn if initially Jarvis is facing  north

    }
