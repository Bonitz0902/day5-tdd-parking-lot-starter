package com.parkinglot;

import com.parkinglot.exepction.UnrecognizedTicketException;

import java.util.*;

public class SuperParkingBoy {
    List<ParkingLot> parkingLotList;

    public SuperParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket parkCar(Car car) {

        ParkingLot parkingLot = parkCarAndGetParkingLotWithLargestAvailableSlotRate(parkingLotList);
        return parkingLot.parkCar(car);
    }

    //TODO: change the function name parkCarAnd*. Remove parkCar
    private ParkingLot parkCarAndGetParkingLotWithLargestAvailableSlotRate(List<ParkingLot> parkingLotList){
        return parkingLotList.stream()
                .max(Comparator.comparingDouble(ParkingLot::getAvailableSlotRate))
                .orElse(null);
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
