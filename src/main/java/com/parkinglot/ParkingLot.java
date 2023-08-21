package com.parkinglot;

import com.parkinglot.exepction.NoAvailablePositionException;
import com.parkinglot.exepction.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    //TODO: check warnings
    //TODO: use overloading to create default maxSlot of 10, which is in the story
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private int nextTicketNumber = 1;
    private int maxSlot;
    private int parkingLotNumber;
    private int availableParkingSlot;

    public ParkingTicket parkCar(Car car) {

        if (parkingTicketCarMap.size() >= maxSlot) {
            throw new NoAvailablePositionException();
        }

        ParkingTicket parkingTicket = new ParkingTicket(nextTicketNumber, parkingLotNumber);
        parkingTicketCarMap.put(parkingTicket, car);
        nextTicketNumber++;
        availableParkingSlot--;
        return parkingTicket;
    }

    public Car retrieveCar(ParkingTicket ticket) {
        Car car = parkingTicketCarMap.get(ticket);

        if(car == null){
            throw new UnrecognizedTicketException();
        }
        return parkingTicketCarMap.remove(ticket);
    }

    public Map<ParkingTicket, Car> getParkingTicketCarMap(){
        return parkingTicketCarMap;
    }

    public int getParkingTicketCarMapSize(){

        return parkingTicketCarMap.size();
    }

    public int getParkingLotSize(){
        return this.maxSlot;
    }

    public ParkingLot(int maxSlot, int parkingLotNumber) {
        this.maxSlot = maxSlot;
        this.parkingLotNumber = parkingLotNumber;
        this.availableParkingSlot = maxSlot;
    }

    public double getAvailableSlotRate() {
        return (double) availableParkingSlot/maxSlot;
    }

    public Car setParkingTicketCarMap(Map<ParkingTicket, Car> ticketCarMap, ParkingTicket parkingTicket){
        Car car = ticketCarMap.remove(parkingTicket);
        this.parkingTicketCarMap = ticketCarMap;
        return car;
    }
}
