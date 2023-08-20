package com.parkinglot;

import com.parkinglot.exepction.UnrecognizedTicketException;

import java.util.*;

public class SmartParkingBoy {

    static List<ParkingLot> parkingLotsList;

    static Map<Car, ParkingLot> carParkingLotMap = new HashMap<>();
    public List<Map<ParkingTicket, Car>> parkingTicketCarMap = new ArrayList<>();

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {

        this.parkingLotsList = parkingLotList;
    }

    public ParkingTicket parkCar(Car car) {

        ParkingLot parkingLotWithLowestParkedCars = findParkingLotWithLowestParkedCars(parkingLotsList);
        return parkingLotWithLowestParkedCars.parkCar(car);


    }


    private static ParkingLot findParkingLotWithLowestParkedCars(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .min(Comparator.comparingInt(ParkingLot::getParkingTicketCarMapSize))
                .orElse(null);

    }

    public static Car retrieveCar(ParkingTicket ticket){
        Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

        return parkingLotsList.stream()
                .map(parkingLot ->{
                    parkingTicketCarMap.putAll(parkingLot.getParkingTicketCarMap());
                    return parkingLot.setParkingTicketCarMap(parkingTicketCarMap,ticket);
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
    }

}
