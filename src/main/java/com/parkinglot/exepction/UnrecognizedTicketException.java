package com.parkinglot.exepction;

public class UnrecognizedTicketException extends RuntimeException{

    public UnrecognizedTicketException(){
        super("Unrecognized parking ticket.");
    }
}
