package com.gmail.at.irotech;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.health.ApplicationHealthIndicator;
import org.springframework.boot.actuate.health.Status;

import static org.junit.Assert.assertEquals;

public class RoboHooverApplicationTest {

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void actuatorHealthCheckTest() throws Exception {
        ApplicationHealthIndicator healthIndicator = new ApplicationHealthIndicator();
        assertEquals(Status.UP, healthIndicator.health().getStatus());
    }

}
