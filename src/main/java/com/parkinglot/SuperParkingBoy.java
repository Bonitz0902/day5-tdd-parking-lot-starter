package com.parkinglot;

import com.parkinglot.exepction.UnrecognizedTicketException;

import java.util.*;

public class SuperParkingBoy {
    private int maxSlot;
    private int availableSot;
    List<ParkingLot> parkingLotList;

    public SuperParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket parkCar(Car car) {

        ParkingLot parkingLot = parkCarAndGetParkingLotWithLargestAvailableSlotRate(parkingLotList);
        return parkingLot.parkCar(car);
    }

    private ParkingLot parkCarAndGetParkingLotWithLargestAvailableSlotRate(List<ParkingLot> parkingLotList){
        return parkingLotList.stream()
                .max(Comparator.comparingDouble(ParkingLot::getAvailableSlotRate))
                .orElse(null);
    }

    private double getAvailableSlotRate(){
        return (double) availableSot/maxSlot;
    }


    public Car retrieveCar(ParkingTicket ticket) {
        Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
        return parkingLotList.stream()
                .map(parkingLot ->{
                    parkingTicketCarMap.putAll(parkingLot.getParkingTicketCarMap());
                    return parkingLot.setParkingTicketCarMap(parkingTicketCarMap,ticket);
                        })
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);

    }
}
