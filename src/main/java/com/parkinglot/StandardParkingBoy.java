package com.parkinglot;

public class StandardParkingBoy {
    ParkingLot parkingLot;

    public StandardParkingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    public Car retrievedCar(ParkingTicket ticket){
        return parkingLot.retrieveCar(ticket);
    }

    public ParkingTicket parkingTicket(Car car){
        return parkingLot.parkCar(car);
    }


}
