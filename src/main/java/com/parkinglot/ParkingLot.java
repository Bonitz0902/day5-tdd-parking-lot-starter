package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int nextTicketNumber = 1;
    private int maxSlot;

    public ParkingTicket parkCar(Car car) {

        if (parkingTicketCarMap.size() >= maxSlot) {
            return null;
        }

        ParkingTicket parkingTicket = new ParkingTicket(nextTicketNumber);
        parkingTicketCarMap.put(parkingTicket, car);
        nextTicketNumber++;
        return parkingTicket;
    }

    public Car retrieveCar(ParkingTicket ticket) {
        if(ticket == null){
            return null;
        }
        return parkingTicketCarMap.remove(ticket);
    }

    public ParkingLot(int maxSlot) {
        this.maxSlot = maxSlot;
    }
}
