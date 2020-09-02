package com.tingco.codechallenge.elevator.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ElevatorRequest.ElevatorRequestBuilder.class)
public class ElevatorRequest {
    int fromFloor;
    int toFloor;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPOJOBuilder(withPrefix = "")
    public static class ElevatorRequestBuilder {
    }

    public boolean isValid(int maxFloor) {
        return fromFloor >= 1 && fromFloor <= maxFloor && toFloor >= 1 && toFloor <= maxFloor;
    }
}
