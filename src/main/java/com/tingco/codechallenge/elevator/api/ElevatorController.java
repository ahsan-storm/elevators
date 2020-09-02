package com.tingco.codechallenge.elevator.api;

/**
 * Interface for the Elevator Controller.
 *
 * @author Sven Wesley
 *
 */
public interface ElevatorController {

    /**
     * Request an elevator to - from a specific floor
     *
     * @param request - To and from floor as elevator request
     */
    void requestElevator(ElevatorRequest request);
}
