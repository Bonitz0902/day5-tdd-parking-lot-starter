package com.parkinglot.exepction;

public class NoAvailablePositionException extends RuntimeException{

    public NoAvailablePositionException(){
        super("No available position.");
    }
}
