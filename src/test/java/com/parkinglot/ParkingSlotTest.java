package com.parkinglot;

import com.parkinglot.exepction.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotTest {
    ParkingLot parkingLot = new ParkingLot(10);
    String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    @Test
    public void should_return_parking_ticket_when_execute_parkCar_given_parking_lot_and_car(){
        Car car = new Car("123ABC");
        ParkingTicket ticket = parkingLot.parkCar(car);


        assertNotNull(ticket);

    }

    @Test
    public void should_return_parked_car_when_execute_retrieve_car_given_parking_lot_with_parked_car_and_ticket(){
        Car car = new Car("123ABC");
        ParkingTicket ticket = parkingLot.parkCar(car);

        Car retreivedCar = parkingLot.retrieveCar(ticket);

        assertEquals(car, retreivedCar);
    }

    @Test
    public void should_return_right_car_each_ticket_when_execute_retrieve_car_given_2_parked_cars_and_2_parking_tickets(){
        Car car1 = new Car("123123");
        Car car2 = new Car("ABCABC");

        ParkingTicket ticket1 = parkingLot.parkCar(car1);
        ParkingTicket ticket2 = parkingLot.parkCar(car2);

        Car retrievedCar1 = parkingLot.retrieveCar(ticket1);
        Car retrievedCar2 = parkingLot.retrieveCar(ticket2);

        assertEquals(car1,retrievedCar1);
        assertEquals(car2,retrievedCar2);

    }

    @Test
    public void should_return_null_car_when_retrieve_car_given_parking_lot_and_a_wrong_ticket(){
        ParkingTicket wrongTicket = new ParkingTicket(null);


        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                parkingLot.retrieveCar(wrongTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_return_null_car_when_execute_retrieveCar_given_parking_lot_and_used_parking_ticket(){
        Car car1 = new Car("AAAAA");

        ParkingTicket parkingTicket1 = parkingLot.parkCar(car1);

        Car retrivedCar = parkingLot.retrieveCar(parkingTicket1);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                parkingLot.retrieveCar(parkingTicket1));

        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_return_nothing_when_retrived_car_given_null_parking_lot_and_parking_ticket(){
        ParkingLot nullParkingLot = new ParkingLot(0);
        Car car1 = new Car("AAAAA");

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                nullParkingLot.parkCar(car1));

        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());

    }

}
