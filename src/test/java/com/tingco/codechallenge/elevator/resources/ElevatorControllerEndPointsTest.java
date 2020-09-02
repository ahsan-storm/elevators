package com.tingco.codechallenge.elevator.resources;

import com.tingco.codechallenge.elevator.api.ElevatorRequest;
import com.tingco.codechallenge.elevator.service.ElevatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tingco.codechallenge.elevator.config.ElevatorApplication;

/**
 * Boiler plate test class to get up and running with a test faster.
 *
 * @author Sven Wesley
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ElevatorApplication.class)
public class ElevatorControllerEndPointsTest {

    @Autowired
    private ElevatorControllerEndPoints endPoints;

    @Autowired
    private ElevatorService elevatorService;

    @Test
    public void ping() {
        Assert.assertEquals("pong", endPoints.ping());
    }

    @Test
    public void testNewRequest() {
        ElevatorRequest elevatorRequest = ElevatorRequest.builder().toFloor(15).fromFloor(5).build();
        endPoints.requestElevator(elevatorRequest);
        Assert.assertEquals(elevatorRequest, elevatorService.topRequest());
    }
}
