package com.parkinglot;

public class ParkingTicket {
    private Integer ticketNumber;
    private int parkingLotNumber;

    public ParkingTicket(Integer ticketNumber, int parkingLotNumber) {
        this.ticketNumber = ticketNumber;
        this.parkingLotNumber = parkingLotNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }
}
