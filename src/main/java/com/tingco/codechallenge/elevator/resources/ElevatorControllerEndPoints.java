package com.tingco.codechallenge.elevator.resources;

import com.tingco.codechallenge.elevator.api.ElevatorController;
import com.tingco.codechallenge.elevator.api.ElevatorRequest;
import com.tingco.codechallenge.elevator.service.ElevatorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Resource.
 *
 * @author Sven Wesley
 *
 */
@RestController
@RequestMapping("/rest/v1")
public final class ElevatorControllerEndPoints implements ElevatorController {

    private final ElevatorService elevatorService;

    public ElevatorControllerEndPoints(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    /**
     * Ping service to test if we are alive.
     *
     * @return String pong
     */
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }

    @Override
    @RequestMapping(value = "/request-elevator", method = RequestMethod.POST)
    public void requestElevator(@RequestBody ElevatorRequest request) {
        elevatorService.addNewRequest(request);
    }
}
