package com.parkinglot;

import com.parkinglot.exepction.NoAvailablePositionException;
import com.parkinglot.exepction.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotTest {
    ParkingLot parkingLot = new ParkingLot(10);
    String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    String NO_AVAILABLE_POSITION = "No available position.";

    //STORY 1 and 2
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
        Car car = parkingLot.retrieveCar(parkingTicket1);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                parkingLot.retrieveCar(parkingTicket1));

        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_return_nothing_when_retrived_car_given_null_parking_lot_and_parking_ticket(){
        ParkingLot nullParkingLot = new ParkingLot(0);
        Car car1 = new Car("AAAAA");

        NoAvailablePositionException unrecognizedTicketException = assertThrows(NoAvailablePositionException.class, () ->
                nullParkingLot.parkCar(car1));

        assertEquals(NO_AVAILABLE_POSITION, unrecognizedTicketException.getMessage());

    }

    // Story 3

    @Test
    public void should_return_parkingTicket_when_execute_parkCar_given_parkingLot_standardParkingBoy_car(){
        Car car = new Car("AAAAAAA");
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);

        ParkingTicket parkingTicket = standardParkingBoy.parkingTicket(car);

        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_parkedCar_when_execute_retrievedParkCar_given_parkingLot_with_parked_car_standardParkingBoy_car() {
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);

        Car car = new Car("AAAAAA");
        ParkingTicket parkingTicket = standardParkingBoy.parkingTicket(car);
        Car retrievedCar = standardParkingBoy.retrievedCar(parkingTicket);

        assertEquals(car, retrievedCar);
    }

    @Test
    public void should_return_correct_car_each_ticket_when_execute_retrieved_car_given_parkingBoy_parkingLot_multiple_parkingTicklet(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);

        Car car1 = new Car("123123");
        Car car2 = new Car("ABCABC");

        ParkingTicket parkingTicket1 = standardParkingBoy.parkingTicket(car1);
        ParkingTicket parkingTicket2 = standardParkingBoy.parkingTicket(car2);

        Car retrievedCar1 = standardParkingBoy.retrievedCar(parkingTicket1);
        Car retrievedCar2 = standardParkingBoy.retrievedCar(parkingTicket2);

        assertEquals(car1,retrievedCar1);
        assertEquals(car2,retrievedCar2);

    }

    @Test
    public void should_throw_exception_when_execute_retrievedCar_given_standardParkingBoy_with_parkingLot_wrong_ticket(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket wrongTicket = standardParkingBoy.parkingTicket(null);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.retrievedCar(wrongTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_throw_exception_when_execute_retrievedCar_given_used_ticket_standardParkingBoy_with_parkingLot(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car("123123");
        ParkingTicket parkingTicket = standardParkingBoy.parkingTicket(car);

        Car retrievedCar = standardParkingBoy.retrievedCar(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.retrievedCar(parkingTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_throw_exception_when_execute_parkCar_given_parkingBoy_with_null_parkingLot_car(){
        ParkingLot nullParkingLot = new ParkingLot(0);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(nullParkingLot);

        Car car = new Car("123123");

        NoAvailablePositionException unrecognizedTicketException = assertThrows(NoAvailablePositionException.class, () ->
                standardParkingBoy.parkingTicket(car));

        assertEquals(NO_AVAILABLE_POSITION, unrecognizedTicketException.getMessage());

    }

    // Story 4



}
