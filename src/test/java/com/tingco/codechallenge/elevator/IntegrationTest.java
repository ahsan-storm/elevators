package com.tingco.codechallenge.elevator;

import com.tingco.codechallenge.elevator.api.ElevatorRequest;
import com.tingco.codechallenge.elevator.api.ElevatorThread;
import com.tingco.codechallenge.elevator.service.ElevatorService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tingco.codechallenge.elevator.config.ElevatorApplication;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Boiler plate test class to get up and running with a test faster.
 *
 * @author Sven Wesley
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ElevatorApplication.class)
public class IntegrationTest {

    @Autowired
    private ElevatorService elevatorService;

    @Test
    public void simulateAnElevatorShaft() {
        elevatorService.addNewRequest(ElevatorRequest.builder().fromFloor(1).toFloor(10).build());
        elevatorService.addNewRequest(ElevatorRequest.builder().fromFloor(5).toFloor(7).build());
        elevatorService.addNewRequest(ElevatorRequest.builder().fromFloor(1).toFloor(2).build());
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(100000000);
    }
}
