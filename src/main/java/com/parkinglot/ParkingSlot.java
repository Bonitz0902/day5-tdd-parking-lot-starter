package com.parkinglot;

public class ParkingSlot {
   private int slotNumer;

    public int getSlotNumber() {
        return slotNumer;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupySlot(){
        occupied = true;
    }
    public void freeSlot(){
        occupied = false;
    }

    private boolean occupied;


    public ParkingSlot(int slotNumer) {
        this.slotNumer = slotNumer;
        this.occupied = occupied;
    }
}
