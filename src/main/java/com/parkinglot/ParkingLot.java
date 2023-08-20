package com.parkinglot;

import com.parkinglot.exepction.NoAvailablePositionException;
import com.parkinglot.exepction.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int nextTicketNumber = 1;
    private int maxSlot;

    public ParkingTicket parkCar(Car car, int parkingLotNumber) {

        if (parkingTicketCarMap.size() >= maxSlot) {
            throw new NoAvailablePositionException();
        }

        ParkingTicket parkingTicket = new ParkingTicket(nextTicketNumber, parkingLotNumber);
        parkingTicketCarMap.put(parkingTicket, car);
        nextTicketNumber++;
        return parkingTicket;
    }

    public Car retrieveCar(ParkingTicket ticket) {
        Car car = parkingTicketCarMap.get(ticket);

        if(car == null){
            throw new UnrecognizedTicketException();
        }
        return parkingTicketCarMap.remove(ticket);
    }

    public int getParkingTicketCarMap(){
        return parkingTicketCarMap.size();
    }

    public int getParkingLotSize(){
        return this.maxSlot;
    }

    public ParkingLot(int maxSlot) {
        this.maxSlot = maxSlot;
    }
}
