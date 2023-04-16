package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;


public class RegressionTesting {

    CommandHandler commandHandler;
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }
    
  
    @Test
    void testCase1() {
    	
        commandHandler = new CommandHandler();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        commandHandler.initializeSystem(10);
        commandHandler.commands.add("d");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("p");
        commandHandler.runHistory();

        String expectedOutput = "Initializing with size: 10\n" +
                "\n" +
                "History command:i 10\n" +
                "\n" +
                "Initializing with size: 10\n" +
                "\n" +
                "History command:d\n" +
                "\n" +
                "Pen direction going down...\n" +
                "\n" +
                "History command:m5\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:p\n" +
                "\n" +
                "9|                     \n" +
                "8|                     \n" +
                "7|                     \n" +
                "6|                     \n" +
                "5| ↓                   \n" +
                "4| *                   \n" +
                "3| *                   \n" +
                "2| *                   \n" +
                "1| *                   \n" +
                "0| *                   \n" +
                "  ____________________\n" +
                "   0 1 2 3 4 5 6 7 8 9";

        String actualOutput = output.toString().replace("\r\n", "\n").trim();
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testCase2(){
    	 
    	commandHandler = new CommandHandler();
         ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        commandHandler.commands.add("i10");
        commandHandler.commands.add("m1");
        commandHandler.commands.add("m3");
        commandHandler.commands.add("m3");
        commandHandler.commands.add("m1");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        String expectedOutput ="History command:i10\n" +
                "\n" +
                "Initializing with size: 10\n" +
                "\n" +
                "History command:m1\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:m3\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:m3\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:m1\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:p\n" +
                "\n" +
                "9|                     \n" +
                "8| ↑                   \n" +
                "7|                     \n" +
                "6|                     \n" +
                "5|                     \n" +
                "4|                     \n" +
                "3|                     \n" +
                "2|                     \n" +
                "1|                     \n" +
                "0|                     \n" +
                "  ____________________\n" +
                "   0 1 2 3 4 5 6 7 8 9";
        String actualOutput = output.toString().replace("\r\n", "\n").trim();
        assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    void testCase3(){
    	 
    	commandHandler = new CommandHandler();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        commandHandler.commands.add("i5");
        commandHandler.commands.add("d");
        commandHandler.commands.add("c");
        commandHandler.commands.add("u");
        commandHandler.commands.add("c");
        commandHandler.runHistory();
        String expectedOutput ="History command:i5\n" +
                "\n" +
                "Initializing with size: 5\n" +
                "\n" +
                "History command:d\n" +
                "\n" +
                "Pen direction going down...\n" +
                "\n" +
                "History command:c\n" +
                "\n" +
                "Position: 0, 0 - Pen: down - Facing: north\n" +
                "\n" +
                "History command:u\n" +
                "\n" +
                "Pen direction going up...\n" +
                "\n" +
                "History command:c\n" +
                "\n" +
                "Position: 0, 0 - Pen: up - Facing: north";
        String actualOutput = output.toString().replace("\r\n", "\n").trim();
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testCase4(){
    	 
    	commandHandler = new CommandHandler();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        commandHandler.commands.add("i5");
        commandHandler.commands.add("m0");
        commandHandler.commands.add("m2");
        commandHandler.commands.add("m13");
        commandHandler.commands.add("c");
        commandHandler.runHistory();
        String expectedOutput = "History command:i5\n" +
                "\n" +
                "Initializing with size: 5\n" +
                "\n" +
                "History command:m0\n" +
                "\n" +
                "Sorry, I can not understand. Please choose a command from the menu\n" +
                "\n" +
                "History command:m2\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:m13\n" +
                "\n" +
                "Can not move 13 in the north direction, the robot will fall off the table\n" +
                "\n" +
                "History command:c\n" +
                "\n" +
                "Position: 0, 2 - Pen: up - Facing: north";
        String actualOutput = output.toString().replace("\r\n", "\n").trim();
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testCase5(){
    	 
    	commandHandler = new CommandHandler();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        commandHandler.commands.add("i10");
        commandHandler.commands.add("p");
        commandHandler.commands.add("d");
        commandHandler.commands.add("m3");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        String expectedOutput = "History command:i10\n" +
                "\n" +
                "Initializing with size: 10\n" +
                "\n" +
                "History command:p\n" +
                "\n" +
                "9|                     \n" +
                "8|                     \n" +
                "7|                     \n" +
                "6|                     \n" +
                "5|                     \n" +
                "4|                     \n" +
                "3|                     \n" +
                "2|                     \n" +
                "1|                     \n" +
                "0| ↑                   \n" +
                "  ____________________\n" +
                "   0 1 2 3 4 5 6 7 8 9 \n" +
                "History command:d\n" +
                "\n" +
                "Pen direction going down...\n" +
                "\n" +
                "History command:m3\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:p\n" +
                "\n" +
                "9|                     \n" +
                "8|                     \n" +
                "7|                     \n" +
                "6|                     \n" +
                "5|                     \n" +
                "4|                     \n" +
                "3| ↓                   \n" +
                "2| *                   \n" +
                "1| *                   \n" +
                "0| *                   \n" +
                "  ____________________\n" +
                "   0 1 2 3 4 5 6 7 8 9";
        String actualOutput = output.toString().replace("\r\n", "\n").trim();
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testCase6(){
    	 
    	commandHandler = new CommandHandler();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        commandHandler.commands.add("i10");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("p");
        commandHandler.commands.add("d");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("m9");
        commandHandler.commands.add("r");
        commandHandler.commands.add("p");
        commandHandler.runHistory();
        String expectedOutput ="History command:i10\n" +
                "\n" +
                "Initializing with size: 10\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:p\n" +
                "\n" +
                "9|                     \n" +
                "8|                     \n" +
                "7|                     \n" +
                "6|                     \n" +
                "5|                     \n" +
                "4|                     \n" +
                "3|                     \n" +
                "2|                     \n" +
                "1|                     \n" +
                "0| ↑                   \n" +
                "  ____________________\n" +
                "   0 1 2 3 4 5 6 7 8 9 \n" +
                "History command:d\n" +
                "\n" +
                "Pen direction going down...\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:m9\n" +
                "\n" +
                "Jarvis Moving...\n" +
                "\n" +
                "History command:r\n" +
                "\n" +
                "Jarvis Turning right...\n" +
                "\n" +
                "History command:p\n" +
                "\n" +
                "9| * * * * * * * * * * \n" +
                "8| *                 * \n" +
                "7| *                 * \n" +
                "6| *                 * \n" +
                "5| *                 * \n" +
                "4| *                 * \n" +
                "3| *                 * \n" +
                "2| *                 * \n" +
                "1| *                 * \n" +
                "0| ↓ * * * * * * * * * \n" +
                "  ____________________\n" +
                "   0 1 2 3 4 5 6 7 8 9";
        String actualOutput = output.toString().replace("\r\n", "\n").trim();
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testCase7(){
    	 
    	commandHandler = new CommandHandler();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        CommandHandler.handleInput("h", true);
        commandHandler.runHistory();
        String expectedOutput ="Please run a few commands before.";
        String actualOutput = output.toString().replace("\r\n", "\n").trim();
        assertEquals(expectedOutput, actualOutput);
    }
        
}