package com.tingco.codechallenge.elevator.service;

import com.tingco.codechallenge.elevator.api.ElevatorRequest;
import com.tingco.codechallenge.elevator.api.ElevatorThread;
import com.tingco.codechallenge.elevator.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class ElevatorService {

  @Value("${com.tingco.elevator.numberofelevators}")
  private int numberOfElevators;

  @Value("${com.tingco.elevator.maxfloors}")
  private int maxFloors;

  private ConcurrentLinkedQueue<ElevatorRequest> requests = new ConcurrentLinkedQueue<ElevatorRequest>();

  public void startElevatorsOnApplicationStartUp() {
    for (int i = 1; i <= numberOfElevators; i++) {
      Thread elevatorThread = new Thread(new ElevatorThread(requests));
      elevatorThread.setName("Elevator#: " + i);
      elevatorThread.start();
    }
  }

  public void addNewRequest(ElevatorRequest request) {
    if (!request.isValid(maxFloors)) {
      throw new BadRequestException("Invalid floor specified");
    }
    requests.add(request);
  }

  public ElevatorRequest topRequest() {
    if(requests.size()==0)
      return null;
    return requests.peek();
  }
}
