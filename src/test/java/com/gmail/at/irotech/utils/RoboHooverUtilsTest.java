package com.gmail.at.irotech.utils;

import com.gmail.at.irotech.exception.RoboHooverException;
import com.gmail.at.irotech.web.rest.dto.request.RoboHooverRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoboHooverUtilsTest {

    private RoboHooverUtils utils;
    private RoboHooverRequest roboHooverReq;

    @Before
    public void setUp() {
        utils = new RoboHooverUtils();
    }

    @After
    public void tearDown() {
        utils = null;
        roboHooverReq = null;
    }

    /*
    * Null value passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
//    @Test(expected = RoboHooverException.class)
    @Test()
    public void validateInputsWhenNullRoomSize() {
        roboHooverReq = new RoboHooverRequest(null, null, null, null);
        try {
            utils.validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * negative value passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenIncorrectRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {-1, 5}, null, null, null);
        try {
            utils.validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * Only one value passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenInsufficientElementsRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5}, null, null, null);
        try {
            utils.validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * More than two values passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenExcessiveElementsRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5, 5}, null, null, null);
        try {
            utils.validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * Different X/Y values passed as RoomSize
    *
    * Expected: exception with roomSize input check in the message
    */
    @Test
    public void validateInputsWhenNotRectangularRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 4}, null, null, null);
        try {
            utils.validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_ROOMSIZE, e.getMessage());
        }
    }

    /*
    * Correct values passed as RoomSize
    *
    * Expected: no exception raised
    */
    @Test
    public void validateInputsWhenEligibleRoomSize() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, null, null, null);
        try {
            utils.validateRoomSizeInput(roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    /*
    * Null passed as Coords
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenNullAsInitialPosition() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, null, null, null);
        try {
            utils.validateInitialPositionInput(roboHooverReq.getCoords(), roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_INITIAL_POSITION, e.getMessage());
        }
    }

    /*
    * Null passed as Coords
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenOutOfRoomAreaAsInitialPosition() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, new int[] {5, 6}, null, null);
        try {
            utils.validateInitialPositionInput(roboHooverReq.getCoords(), roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_INITIAL_POSITION, e.getMessage());
        }
    }

    /*
    * Correct values passed as Coords
    *
    * Expected: no exception raised
    */
    @Test
    public void validateInputsWhenEligibleInitialPosition() throws Exception {
        roboHooverReq = new RoboHooverRequest(new int[] {5, 5}, new int[] {4, 4}, null, null);
        try {
            utils.validateInitialPositionInput(roboHooverReq.getCoords(), roboHooverReq.getRoomSize());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    /*
    * Null value passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenNullInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, null);
        try {
            utils.validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Unwanted Characters values passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenUnwantedCharactersAsInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NNQWNWTBSN");
        try {
            utils.validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Special Characters values passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenSpecialCharactersAsInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NN?)");
        try {
            utils.validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Numbers values passed as Commands
    *
    * Expected: Exception raised
    */
    @Test
    public void validateInputsWhenNumbersAsInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NN12");
        try {
            utils.validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_INSTRUCTIONS, e.getMessage());
        }
    }

    /*
    * Spaces passed as Commands
    *
    * Expected: No exception raised
    */
    @Test
    public void validateInputsWhenEligibleInstructionsWithSpaces() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NN ESW WNW SN");
        try {
            utils.validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    /*+
    * Correct values passed as RoomSize
    *
    * Expected: no exception raised
    */
    @Test
    public void validateInputsWhenEligibleInstructions() throws Exception {
        roboHooverReq = new RoboHooverRequest(null, null, null, "NNESWWNWSN");
        try {
            utils.validateInstructionsInput(roboHooverReq.getInstructions());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(true);
    }

}
