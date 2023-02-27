package com.java.singleton;

import java.awt.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {
    private static Singleton robot;

    @BeforeEach
    void setUp(){
        robot = Singleton.getInstance();
    }

    @AfterEach
    void TearDown() {
        robot.reinitialize();
    }
//Test case 1 (Checking after setting the direction to north)
    @Test
    void testSetDirectionNorth(){
        robot.setDirectionSouth();
        robot.setDirectionNorth();
        assertEquals("north", robot.getDirection());
    }
  //Test case 2 (Checking after setting the direction to south)
    @Test
    void testSetDirectionSouth(){
        robot.setDirectionSouth();
        assertEquals("south", robot.getDirection());
    }
  //Test case 3 (Checking after setting the direction to east)
    @Test
    void testSetDirectionEast(){
        robot.setDirectionEast();
        assertEquals("east", robot.getDirection());
    }
  //Test case 4(Checking after setting the direction to west)
    @Test
    void testSetDirectionWest(){
        robot.setDirectionWest();
        assertEquals("west", robot.getDirection());
    }

  //Test case 5 (checking pen state false==pen UP: true==pen down
    @Test
    void testGetPenState(){
      
        assertFalse(robot.getPenState());//false== pen state up

        robot.setPenState(true);
       
        assertTrue(robot.getPenState());//true==pen state down
    }

 
    }
