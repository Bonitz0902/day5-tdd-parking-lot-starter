package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotTest {
    @Test
    public void should_return_ticket_when_execute_getParkingTicket_given_car(){
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingSlot parkingTicket = parkingLot.parkVehicle();

        assertNotNull(parkingTicket);

    }

}
