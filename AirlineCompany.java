/**
 * Airplane Airlines simulator
 * @author Addison Chan, Kyle Bascomb
 * @version 11/04/16
 */

import java.lang.System;
import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;

public class AirlineCompany {
    private static Airplane airplane;
    private static ArrayList<Passenger> passengers;

    public static void main (String [] args){
        airplane = new Airplane();
        passengers = new ArrayList<Passenger>();
        Scanner in = new Scanner(System.in);

        randomFillAirplane();

        System.out.println("Welcome to Java Airlines.");
        while(true){
            System.out.println("Please select what you would like to do:");
            System.out.println("1. Display Occupancy");
            System.out.println("2. Reserve seats manually");
            System.out.println("3. Preferential seating arrangement");
            System.out.println("4. Cancel seating by name or seat number");
            System.out.println("5. Print all passenger info");
            System.out.println("6. Print all reserved seats info");
            System.out.println("0. Exit");
            System.out.print("> ");

            int choice = 0; // allows for loop to restart
            try{
                choice = in.nextInt();
            } catch (Exception e){
                System.out.println("Not a choice");
                in.nextLine();
                continue;
            }

            System.out.println();

            if (choice == 0)
                break; // kills loop

            switch (choice){
                case 1:
                    DisplayOccupancy.displayOccupancy(airplane);
                    break;
                case 2:
                    ReserveSeats.reserveSeatsManually(airplane, passengers);
                    break;
                case 3:
                    ReserveSeats.preferentialSeating(airplane, passengers);
                    break;
                case 4:
                    CancelSeats.cancelSeats(airplane, passengers);
                    break;
                case 5:
                    Print.printPassengerInfo(passengers);
                    break;
                case 6:
                    Print.printReservedSeatsInfo(airplane);
                    break;
            }
        }

        System.out.println("Thanks for flying Java!");
    }

    /**
    * Fill the airplane up with seats randomly
    * (Postcondition: fills the airplane with seats)
    * (Precondition: none)
    */
    public static void randomFillAirplane(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of seats to be filled: ");
        int seatsToFill;

        try {
            seatsToFill = in.nextInt();
            if(seatsToFill > 96 || 0 > seatsToFill)
                throw new Exception("Not a valid choice");
        } catch(Exception e) {
            System.out.println("That was not a valid choice.\n");
            return;
        }

        while(seatsToFill > 0){
            int currentSeat = (int)Math.floor(Math.random()*96);
            int column = currentSeat / 8;
            int row = currentSeat % 8;
            if(airplane.getAirplaneSeats()[column][row].isVacant){
                seatsToFill--;
                String firstName = "John " + seatsToFill;
                String lastName = "Doe";
                passengers.add(new Passenger(firstName, lastName, row, column));
                airplane.setAirplaneSeatName(column, row, firstName, lastName);
            }
            // else do nothing
        }

    }
}
