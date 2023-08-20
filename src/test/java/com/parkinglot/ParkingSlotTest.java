package com.parkinglot;

import com.parkinglot.exepction.NoAvailablePositionException;
import com.parkinglot.exepction.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotTest {
    ParkingLot parkingLot = new ParkingLot(10, 1);
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
        ParkingTicket wrongTicket = new ParkingTicket(null, 1);


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
        ParkingLot nullParkingLot = new ParkingLot(0, 1);
        Car car1 = new Car("AAAAA");

        NoAvailablePositionException unrecognizedTicketException = assertThrows(NoAvailablePositionException.class, () ->
                nullParkingLot.parkCar(car1));

        assertEquals(NO_AVAILABLE_POSITION, unrecognizedTicketException.getMessage());

    }

    // Story 3

    @Test
    public void should_return_parkingTicket_when_execute_parkCar_given_parkingLot_standardParkingBoy_car(){
        Car car = new Car("AAAAAAA");
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));

        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_parkedCar_when_execute_retrievedParkCar_given_parkingLot_with_parked_car_standardParkingBoy_car() {
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));

        Car car = new Car("AAAAAA");
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        Car retrievedCar = standardParkingBoy.retrievedCar(parkingTicket);

        assertEquals(car, retrievedCar);
    }

    @Test
    public void should_return_correct_car_each_ticket_when_execute_retrieved_car_given_parkingBoy_parkingLot_multiple_parkingTicklet(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));

        Car car1 = new Car("123123");
        Car car2 = new Car("ABCABC");

        ParkingTicket parkingTicket1 = standardParkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = standardParkingBoy.parkCar(car2);

        Car retrievedCar1 = standardParkingBoy.retrievedCar(parkingTicket1);
        Car retrievedCar2 = standardParkingBoy.retrievedCar(parkingTicket2);

        assertEquals(car1,retrievedCar1);
        assertEquals(car2,retrievedCar2);

    }

    @Test
    public void should_throw_exception_when_execute_retrievedCar_given_standardParkingBoy_with_parkingLot_wrong_ticket(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));
        ParkingTicket wrongTicket = standardParkingBoy.parkCar(null);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.retrievedCar(wrongTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_throw_exception_when_execute_retrievedCar_given_used_ticket_standardParkingBoy_with_parkingLot(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));
        Car car = new Car("123123");
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        Car retrievedCar = standardParkingBoy.retrievedCar(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.retrievedCar(parkingTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_throw_exception_when_execute_parkCar_given_parkingBoy_with_null_parkingLot_car(){
        ParkingLot nullParkingLot = new ParkingLot(0, 1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(nullParkingLot));

        Car car = new Car("123123");

        NoAvailablePositionException unrecognizedTicketException = assertThrows(NoAvailablePositionException.class, () ->
                standardParkingBoy.parkCar(car));

        assertEquals(NO_AVAILABLE_POSITION, unrecognizedTicketException.getMessage());

    }

    // Story 4
    @Test
    public void should_return_parkingTicket_parked_in_the_first_parkingLot_when_execute_parkingTicket_given_standardParkingBoy_with_two_parkingLots_car(){

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));

        Car car = new Car("123123");

        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        assertEquals(1, parkingTicket.getParkingLotNumber());

    }

    @Test
    public void should_return_parkingTicket_with_2nd_parkingLotNumber_when_execute_parkingTicket_given_2_parking_lot_with_one_full_one_available_car(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));
        ParkingTicket ticket1;
        Car carThatWillParkOn2ndParkingLot = new Car("im on Lot 2");
        List<Car> tenCars = new ArrayList<>();
        for(int i = 1; i <=10; i++){
            String licensePlate = "AAA" + i;
            tenCars.add(new Car(licensePlate));
        }
        for(Car car : tenCars){
            ticket1 = standardParkingBoy.parkCar(car);
        }

        ParkingTicket parkingTicketOfLot2 = standardParkingBoy.parkCar(carThatWillParkOn2ndParkingLot);

        assertEquals(2,parkingTicketOfLot2.getParkingLotNumber());

    }

    @Test
    public void should_return_right_car_each_ticket_when_execute_retrievedCar_given_2_cars_parkingBoy_with_2_parkingLots(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));

        Car car1 = new Car("AAA");
        Car car2 = new Car("BBB");

        ParkingTicket parkingTicket1 = standardParkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = standardParkingBoy.parkCar(car2);

        Car retrievedCar1 = standardParkingBoy.retrievedCar(parkingTicket1);
        Car retrievedCar2 = standardParkingBoy.retrievedCar(parkingTicket2);

        assertEquals(car1, retrievedCar1);
        assertEquals(car2, retrievedCar2);

    }

    @Test
    public void should_throw_exception_when_execute_retrievedCar_given_parkingBoy_with_2_parkingLots_unrecognized_ticket(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));

        ParkingTicket wrongTicket = standardParkingBoy.parkCar(null);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.retrievedCar(wrongTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_throw_exception_when_execute_retrievedCar_given_parkingBoy_with_2_parkingLots_used_ticket(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));

        Car car = new Car("123123");
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        Car retrievedCar = standardParkingBoy.retrievedCar(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                standardParkingBoy.retrievedCar(parkingTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_throw_exception_when_execute_retrievedCar_given_parkingBoy_with_2_null_parkingLots_used_ticket(){
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(0, 1),new ParkingLot(0, 2)));
        Car car = new Car("123123");

        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, ()->
                standardParkingBoy.parkCar(car));

        assertEquals(NO_AVAILABLE_POSITION, noAvailablePositionException.getMessage());
    }

    // Story 5
    @Test
    public void should_return_parking_ticket_when_execute_parkCar_given_parking_lot_and_car_smartParkingBoy(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));
        Car car = new Car("123ABC");
        ParkingTicket ticketThatParkedInOne = smartParkingBoy.parkCar(car);

        assertEquals(1,ticketThatParkedInOne);



    }

    @Test
    public void should_return_parked_car_when_execute_retrieve_car_given_parking_lot_with_parked_car_and_ticket_smartParkingBoy(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));

        Car car = new Car("123ABC");
        ParkingTicket ticket = smartParkingBoy.parkCar(car);

        Car retreivedCar = smartParkingBoy.retrieveCar(ticket);

        assertEquals(car, retreivedCar);
    }

    @Test
    public void should_return_right_car_each_ticket_when_execute_retrieve_car_given_2_parked_cars_and_2_parking_tickets_smartParkingBoy(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));
        Car car1 = new Car("123123");
        Car car2 = new Car("ABCABC");

        ParkingTicket ticket1 = smartParkingBoy.parkCar(car1);
        ParkingTicket ticket2 = smartParkingBoy.parkCar(car2);

        Car retrievedCar2 = smartParkingBoy.retrieveCar(ticket2);
        Car retrievedCar1 = smartParkingBoy.retrieveCar(ticket1);


        assertEquals(car1,retrievedCar1);
        assertEquals(car2,retrievedCar2);

    }

    @Test
    public void should_return_null_car_when_retrieve_car_given_parking_lot_and_a_wrong_ticket_smartParkingBoy(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));

        ParkingTicket wrongTicket = new ParkingTicket(null, 1);


        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                smartParkingBoy.retrieveCar(wrongTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_return_null_car_when_execute_retrieveCar_given_parking_lot_and_used_parking_ticket_smartParkingBoy(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(10, 1),new ParkingLot(10, 2)));

        Car car1 = new Car("AAAAA");

        ParkingTicket parkingTicket1 = smartParkingBoy.parkCar(car1);
        Car car = smartParkingBoy.retrieveCar(parkingTicket1);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                smartParkingBoy.retrieveCar(parkingTicket1));

        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_return_nothing_when_retrived_car_given_null_parking_lot_and_parking_ticket_smartParkingBoy(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(0, 1)));
        Car car1 = new Car("AAAAA");

        NoAvailablePositionException unrecognizedTicketException = assertThrows(NoAvailablePositionException.class, () ->
                smartParkingBoy.parkCar(car1));

        assertEquals(NO_AVAILABLE_POSITION, unrecognizedTicketException.getMessage());

    }

    //Story 6

    @Test
    public void should_return_parking_ticket_when_execute_parkCar_given_parking_lot_and_car_superParkingBoy(){
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(10,1)));
        Car car = new Car("123ABC");
        ParkingTicket ticket = superParkingBoy.parkCar(car);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_parked_car_when_execute_retrieve_car_given_parking_lot_with_parked_car_and_ticket_superParkingBoy(){
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(3,1),new ParkingLot(10,2),new ParkingLot(10,3)));

        Car car = new Car("123ABC");
        ParkingTicket ticket1 = superParkingBoy.parkCar(car);

        Car retreivedCar = superParkingBoy.retrieveCar(ticket1);

        assertEquals(car, retreivedCar);
    }

    @Test
    public void should_return_right_car_each_ticket_when_execute_retrieve_car_given_2_parked_cars_and_2_parking_tickets_superParkingBoy(){
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(3,1),new ParkingLot(10,2),new ParkingLot(10,3)));
        Car car1 = new Car("123123");
        Car car2 = new Car("ABCABC");

        ParkingTicket ticket1 = superParkingBoy.parkCar(car1);
        ParkingTicket ticket2 = superParkingBoy.parkCar(car2);


        Car retrievedCar1 = superParkingBoy.retrieveCar(ticket1);
        Car retrievedCar2 = superParkingBoy.retrieveCar(ticket2);


        assertEquals(car1,retrievedCar1);
        assertEquals(car2,retrievedCar2);

    }

    @Test
    public void should_return_null_car_when_retrieve_car_given_parking_lot_and_a_wrong_ticket_superParkingBoy(){
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(3,1),new ParkingLot(10,2),new ParkingLot(10,3)));

        ParkingTicket wrongTicket = new ParkingTicket(null, 1);


        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                superParkingBoy.retrieveCar(wrongTicket));


        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_return_null_car_when_execute_retrieveCar_given_parking_lot_and_used_parking_ticket_superParkingBoy(){
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(3,1),new ParkingLot(10,2),new ParkingLot(10,3)));

        Car car1 = new Car("AAAAA");

        ParkingTicket parkingTicket1 = superParkingBoy.parkCar(car1);
        Car car = superParkingBoy.retrieveCar(parkingTicket1);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () ->
                superParkingBoy.retrieveCar(parkingTicket1));

        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    public void should_return_nothing_when_retrived_car_given_null_parking_lot_and_parking_ticket_superParkingBoy(){
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(0,1)));
        Car car1 = new Car("AAAAA");

        NoAvailablePositionException unrecognizedTicketException = assertThrows(NoAvailablePositionException.class, () ->
                superParkingBoy.parkCar(car1));

        assertEquals(NO_AVAILABLE_POSITION, unrecognizedTicketException.getMessage());

    }

}
