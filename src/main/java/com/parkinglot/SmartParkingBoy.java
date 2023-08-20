package com.parkinglot;

import com.parkinglot.exepction.UnrecognizedTicketException;

import java.util.*;

public class SmartParkingBoy {

    List<ParkingLot> parkingLotsList;
    //static ParkingLot parkingLotWithLowestParkedCars;

    static Map<Car, ParkingLot> carParkingLotMap = new HashMap<>();
    public List<Map<ParkingTicket, Car>> parkingTicketCarMap = new ArrayList<>();

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotsList = parkingLotList;
    }

    public ParkingTicket parkCar(Car car) {

        ParkingLot parkingLotWithLowestParkedCars = findParkingLotWithLowestParkedCars(parkingLotsList);
        this.carParkingLotMap.put(car, parkingLotWithLowestParkedCars);

        return parkingLotWithLowestParkedCars.parkCar(car);


    }


    private static ParkingLot findParkingLotWithLowestParkedCars(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .min(Comparator.comparingInt(ParkingLot::getParkingTicketCarMapSize))
                .orElse(null);

    }

    public static Car retrieveCar(ParkingTicket ticket){
        if(carParkingLotMap.isEmpty()){
            throw new UnrecognizedTicketException();
        }
        for(Map.Entry<Car,ParkingLot> entry: carParkingLotMap.entrySet()){
            Car car = entry.getKey();
            if(entry.getValue().retrieveCar(ticket) != null){
                carParkingLotMap.remove(car);
                return car;
            }
        }
        return null;
    }

}
