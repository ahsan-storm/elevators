package com.tingco.codechallenge.elevator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class responsible for starting elevators on app start with quantity mentioned in properties file
 */
@Component
public class AppStartUpRunner implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(AppStartUpRunner.class);

  private final ElevatorService elevatorService;

  public AppStartUpRunner(ElevatorService elevatorService) {
    this.elevatorService = elevatorService;
  }

  @Override
  public void run(String... strings) {
    LOG.info("Starting elevators on application startup");
    elevatorService.startElevatorsOnApplicationStartUp();
  }
}
