package com.parkinglot;

import com.parkinglot.exepction.NoAvailablePositionException;

import java.util.List;

public class StandardParkingBoy {
    List<ParkingLot> parkingLotList;

    public StandardParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }
    public Car retrievedCar(ParkingTicket ticket){
        for (ParkingLot parkingLot1 : parkingLotList){
            return parkingLot1.retrieveCar(ticket);
        }
        return null;
    }

    //TODO: this throws a NoAvailablePositionException when the maxSlot (not available slot) of the parking lot is 0.
    public ParkingTicket parkCar(Car car) {

        for (ParkingLot parkingLot1 : parkingLotList) {
            if(parkingLot1.getParkingLotSize() == 0){
                throw new NoAvailablePositionException();
            }
            if (parkingLot1.getParkingTicketCarMapSize() != parkingLot1.getParkingLotSize()) {
                return parkingLot1.parkCar(car);
            }

        }

        return null;
    }

}
