package com.gmail.at.irotech.service;

import com.gmail.at.irotech.exception.RoboHooverException;
import com.gmail.at.irotech.utils.RoboHooverConstants;
import com.gmail.at.irotech.vo.RoboHooverInputsVO;
import com.gmail.at.irotech.web.rest.dto.request.RoboHooverRequest;
import com.gmail.at.irotech.web.rest.dto.response.RoboHooverError;
import com.gmail.at.irotech.web.rest.dto.response.RoboHooverResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class RoboHooverServiceTest {

    RoboHooverService service;
    RoboHooverRequest request;

    @Before
    public void setUp() throws Exception {
        service = new RoboHooverService();
    }

    @After
    public void tearDown() throws Exception {
        request = null;
    }

    @Test
    public void runCleaningWithNullAsInputParams() throws Exception {
        final RoboHooverResponse roboHooverResponse = service.runCleaning(null);
        assertEquals(new RoboHooverResponse(null, 0, Arrays.asList(new RoboHooverError(
                false, RoboHooverConstants.ERROR_INPUT_SERVICE,""))), roboHooverResponse);
    }

    @Test
    public void runCleaningWithWithWrongRoomSize() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 6},
                new int[] {1, 1},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "NSSN");
        try {
            final RoboHooverResponse roboHooverResponse = service.runCleaning(request);
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_ROOMSIZE, e.getMessage());
        }
    }

    @Test
    public void runCleaningWithWithWrongInitialPosition() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 5},
                new int[] {1, 6},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "NSSN");
        try {
            final RoboHooverResponse roboHooverResponse = service.runCleaning(request);
        } catch (Exception e) {
            assertTrue(e instanceof RoboHooverException);
            assertEquals(RoboHooverConstants.ERROR_INPUT_INITIAL_POSITION, e.getMessage());
        }
    }

    @Test
    public void runCleaningWithSomeInvalidCommandsWithNoStains() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 5},
                new int[] {1, 1},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "NSSN");
        final RoboHooverResponse roboHooverResponse = service.runCleaning(request);
        assertEquals(new RoboHooverResponse(new int[] {1, 1}, 0, null), roboHooverResponse);
    }

    @Test
    public void runCleaningWithSomeInvalidCommandsWith2StainsRemoved() throws Exception {
        request = new RoboHooverRequest(
                new int[] {5, 5},
                new int[] {1, 1},
                new int[][] {{2, 3},{3, 2},{3, 3}},
                "EENNEEE");
        final RoboHooverResponse roboHooverResponse = service.runCleaning(request);
        assertEquals(new RoboHooverResponse(new int[] {5, 3}, 2, null), roboHooverResponse);
    }

}
