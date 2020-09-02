package com.tingco.codechallenge.elevator.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ElevatorThread implements Elevator, Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(ElevatorThread.class);
  private static final int TRANSIT_TIME = 1000;

  private Direction direction;
  private int currentFloor;
  private ConcurrentLinkedQueue<ElevatorRequest> requests;
  private TreeSet<Integer> destinations;

  public ElevatorThread(ConcurrentLinkedQueue<ElevatorRequest> requests) {
    this.requests = requests;
    this.direction = Direction.NONE;
    this.currentFloor = 1;
    this.destinations = new TreeSet<>();
  }

  @Override
  public Direction getDirection() {
    return direction;
  }

  @Override
  public int currentFloor() {
    return currentFloor;
  }

  @Override
  public void run() {
      LOGGER.info("Running thread: {}", Thread.currentThread().getName());
      try {
        while (true) {
          ElevatorRequest elevatorRequest = requests.poll();
          if (elevatorRequest != null) {
            Integer destination = elevatorRequest.getFromFloor();
            destinations.add(elevatorRequest.getToFloor());

            LOGGER.info("Processing of Request " + elevatorRequest + "started");
            Thread.sleep(TRANSIT_TIME);

            while (destination != null) {
              LOGGER.info("Going to floor: {}", destination);

              while (currentFloor() != destination) {
                if (currentFloor() > destination)
                  this.moveDown();
                else if (currentFloor() < destination)
                  this.moveUp();
              }
              this.openDoor();
              this.closeDoor();

              destination = destinations.pollFirst();
            }
          }
        }
      } catch (InterruptedException e) {
        LOGGER.error("Elevator thread interrupted: ", e);
      }
  }

   private void moveUp() throws InterruptedException {
    this.direction = Direction.UP;
    printStatusInfo();
    Thread.sleep(TRANSIT_TIME);
    this.currentFloor = ++this.currentFloor;
    this.direction = Direction.NONE;
  }

  private void moveDown() throws InterruptedException {
    this.direction = Direction.DOWN;
    printStatusInfo();
    Thread.sleep(TRANSIT_TIME);
    this.currentFloor = --this.currentFloor;
    this.direction = Direction.NONE;
  }

  private void openDoor() throws InterruptedException {
    this.direction = Direction.OPEN;
    this.printStatusInfo();
    Thread.sleep(TRANSIT_TIME);
  }

  private void closeDoor() throws InterruptedException {
    this.direction = Direction.CLOSE;
    this.printStatusInfo();
    Thread.sleep(TRANSIT_TIME);
  }

  private void printStatusInfo() {
    LOGGER.info(Thread.currentThread().getName() + ": is on Level:" + this.currentFloor() + " Status:" + this.getDirection());
  }
}
