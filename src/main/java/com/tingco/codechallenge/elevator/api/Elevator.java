package com.tingco.codechallenge.elevator.api;

/**
 * Interface for an elevator object.
 *
 * @author Sven Wesley
 *
 */
interface Elevator {

    /**
     * Enumeration for describing elevator's direction.
     */
    enum Direction {
        UP, DOWN, NONE, OPEN, CLOSE
    }

    /**
     * Tells which direction is the elevator going in.
     *
     * @return Direction Enumeration value describing the direction.
     */
    Direction getDirection();

    /**
     * Reports which floor the elevator is at right now.
     *
     * @return int actual floor at the moment.
     */
    int currentFloor();
}
