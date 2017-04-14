package com.gmail.at.irotech.domain.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.util.*;

import static org.junit.Assert.*;

public class RoboHooverTest {

    private Room room;
    private RoboHoover hoover;
    private List<Character> commands;

    @Before
    public void setUp() throws Exception {
        room = new Room(new Point(5, 5),
                new HashSet<>(Arrays.asList(
                        new Point(2, 3),
                        new Point(3, 2),
                        new Point(3, 3)
                )));
    }

    @After
    public void tearDown() throws Exception {
        room = null;
        hoover = null;
        commands = null;
    }

    @Test
    public void testHooverInitialPosition() {
        hoover = new RoboHoover(new Point(5, 5), room);
        assertEquals(new Point(5, 5), this.room.getHooverPosition());
    }

    @Test
    public void executeCommandsWithSomeInvalidMovesWithNoStains() {
        hoover = new RoboHoover(new Point(5, 5), room);
        assertEquals(new Point(5, 5), this.room.getHooverPosition());
        assertEquals(3, room.getStains().size());
        commands = new LinkedList<>(Arrays.asList('N', 'S', 'S', 'N'));
        hoover.executeCommands(commands);
        assertEquals(new Point(5, 4), this.room.getHooverPosition());
        assertEquals(3, room.getStains().size());
        assertEquals(0, this.room.getStainRemovalCount());
    }

    @Test
    public void executeCommandsWithSomeInvalidMovesWith2StainsRemoved() {
        hoover = new RoboHoover(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        assertEquals(3, room.getStains().size());
        commands = new LinkedList<>(Arrays.asList('E', 'E', 'N', 'N', 'E', 'E', 'E'));
        hoover.executeCommands(commands);
        assertEquals(new Point(5, 3), this.room.getHooverPosition());
        assertEquals(1, room.getStains().size());
        assertEquals(2, this.room.getStainRemovalCount());
    }

    @Test
    public void executeCommandsWithSomeInvalidMovesWith2StainsSamePosition() {
        room = new Room(new Point(5, 5),
                new HashSet<>(Arrays.asList(
                        new Point(2, 3),
                        new Point(3, 2),
                        new Point(3, 3),
                        new Point(3, 3)
                )));
        hoover = new RoboHoover(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        assertEquals(3, room.getStains().size());
        commands = new LinkedList<>(Arrays.asList('E', 'E', 'N', 'N', 'E', 'E', 'E'));
        hoover.executeCommands(commands);
        assertEquals(new Point(5, 3), this.room.getHooverPosition());
        assertEquals(1, room.getStains().size());
        assertEquals(2, this.room.getStainRemovalCount());
    }

    @Test
    public void testMoveNorthWithNotValidValue() throws Exception {
        hoover = new RoboHoover(new Point(1, 5), room);
        assertEquals(new Point(1, 5), this.room.getHooverPosition());
        Point retVal = hoover.moveNorth();
        assertEquals(new Point(1, 5), retVal);
    }

    @Test
    public void testMoveNorthWithValidValue() throws Exception {
        hoover = new RoboHoover(new Point(4, 1), room);
        assertEquals(new Point(4, 1), this.room.getHooverPosition());
        Point retVal = hoover.moveNorth();
        assertEquals(new Point(4, 2), retVal);
    }

    @Test
    public void testMoveEastWithNotValidValue() throws Exception {
        hoover = new RoboHoover(new Point(5, 5), room);
        assertEquals(new Point(5, 5), this.room.getHooverPosition());
        Point retVal = hoover.moveEast();
        assertEquals(new Point(5, 5), retVal);
    }

    @Test
    public void testMoveEastWithValidValue() throws Exception {
        hoover = new RoboHoover(new Point(4, 1), room);
        assertEquals(new Point(4, 1), this.room.getHooverPosition());
        Point retVal = hoover.moveEast();
        assertEquals(new Point(5, 1), retVal);
    }

    @Test
    public void testMoveSouthWithNotValidValue() throws Exception {
        hoover = new RoboHoover(new Point(1, 0), room);
        assertEquals(new Point(1, 0), this.room.getHooverPosition());
        Point retVal = hoover.moveSouth();
        assertEquals(new Point(1, 0), retVal);
    }

    @Test
    public void testMoveSouthWithValidValue() throws Exception {
        hoover = new RoboHoover(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        Point retVal = hoover.moveSouth();
        assertEquals(new Point(1, 0), retVal);
    }

    @Test
    public void testMoveWestWithNotValidValue() throws Exception {
        hoover = new RoboHoover(new Point(0, 1), room);
        assertEquals(new Point(0, 1), this.room.getHooverPosition());
        Point retVal = hoover.moveWest();
        assertEquals(new Point(0, 1), retVal);
    }

    @Test
    public void testMoveWestWithValidValue() throws Exception {
        hoover = new RoboHoover(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.room.getHooverPosition());
        Point retVal = hoover.moveWest();
        assertEquals(new Point(0, 1), retVal);
    }

}
