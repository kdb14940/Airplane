/**
 * Airplane Airlines simulator
 * @author Addison Chan, Kyle Bascomb
 * @version 11/04/16
 */

import java.util.Scanner;
import java.util.ArrayList;

public class AirlineCompany {
    private static Airplane airplane;
    private static ArrayList<Passenger> passengers;

    public static void main (String [] args){
        airplane = new Airplane();
        passengers = new ArrayList<Passenger>();
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Java Airlines.");
        while(true){
            System.out.println("Please select what you would like to do:");
            System.out.println("1. Display Occupancy");
            System.out.println("2. Reserve seats manually");
            System.out.println("3. Reserve seats automatically");
            System.out.println("4. Preferential seating arrangement");
            System.out.println("5. Cancel seating by name or seat number");
            System.out.println("6. Print all passenger info");
            System.out.println("7. Print all reserved seats info");
            System.out.println("8. Reserve seats by seat class and preferential seating");
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
                    displayOccupancy();
                    break;
                case 2:
                    reserveSeatsManually();
                    break;
                case 3:
                    reserveSeatsAutomatically();
                    break;
                case 4:
                    preferentialSeating();
                    break;
                case 5:
                    cancelSeats();
                    break;
                case 6:
                    printPassengerInfo();
                    break;
                case 7:
                    printReservedSeatsInfo();
                    break;
                case 8:
                    reserveSeatsSpecial();
                    break;
            }
        }

        System.out.println("Thanks for flying Java!");
    }

    // 1
    /**
    * Prints out a table of passengers
    * (Postcondition: Prints out table of passengers)
    * (Precondition: Airplane is initialized)
    */
    public static void displayOccupancy(){

        int vacant = airplane.getAirplaneSeats().length * airplane.getAirplaneSeats()[0].length; // gets number of seats in an airplane
        int occupied = 0;

        airplane.getAirplaneSeats()[1][2].isVacant = false;
        System.out.println("Airplane Layout:");
        System.out.print(" ");
        for(int column = 0; column < airplane.getAirplaneSeats().length; column++){
            System.out.format("%3s", column + 1);
        }
        System.out.println();
        for(int row = 0; row < airplane.getAirplaneSeats()[0].length ; row++){
            System.out.print((char)(row + 65) + " ");
            for(int column = 0; column < airplane.getAirplaneSeats().length ; column++){
                if(airplane.getAirplaneSeats()[column][row].isVacant)
                    System.out.print("[ ]");
                else {
                    vacant--;
                    occupied++;
                    System.out.print("[x]");
                }
            }
            System.out.println();
        }
        System.out.format("Seats Vacant: %s | Seats Occupied: %s", vacant, occupied);
        System.out.println(); // for format
        System.out.println(); // to make newline before prompt again
    }

    // 2
    /**
    * Reserve seats manually
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void reserveSeatsManually(){
        String firstName;
        String lastName;
        int row;
        int column;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your first name: ");
        firstName = in.nextLine();
        System.out.print("Please enter your last name: ");
        lastName = in.nextLine();
        System.out.print("Please enter the seat you would like to reserve, ie. A1: ");
        String userChoice = in.nextLine();

        // get input for seat
        try {
            char rowChar = userChoice.charAt(0);
            if('a' <= rowChar && rowChar <= 'z')
                rowChar = (char) (rowChar - 32);
            else if (rowChar < 'A' || 'Z' < rowChar)
                throw new Exception("Not valid row");
            row = rowChar - 64;

            if (userChoice.length() == 2)
                column = Integer.parseInt(userChoice.substring(1,2));
            else if (userChoice.length() == 3)
                column = Integer.parseInt(userChoice.substring(1,3));
            else
                throw new Exception("not valid input");

            if (column > 12 || column < 1)
                throw new Exception("not valid input");

        } catch(Exception e) {
            System.out.println("That was not a valid input.");
            System.out.println("Your seat was not reserved.");
            System.out.println();
            return;
        }

        if(airplane.getAirplaneSeats()[column-1][row-1].isVacant){
            passengers.add(new Passenger(firstName, lastName, row, column)); // adds new passenger to list of passengers
            airplane.setAirplaneSeatName(column, row, firstName, lastName); // adds passenger to airplane (sets seat name)
            System.out.println("Your seat was reserved.\n");
        } else {
            System.out.println("The seat is already taken.");
            System.out.println("Your seat was not reserved.");
            System.out.println();
            return;
        }

        // Debugging purposes
        // System.out.format("%s%s %s %s",
        //         passengers.get(0).getColumn(),
        //         passengers.get(0).getRow(),
        //         passengers.get(0).getFirstName(),
        //         passengers.get(0).getLastName());

    }

    // 3
    /**
    * Reserve seats automatically
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void reserveSeatsAutomatically(){
        
    }

    // 4
    /**
    * Reserve seats with preferences
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void preferentialSeating(){
        
    }

    // 5
    /**
    * Cancel seats
    * (Postcondition: Cancels seats)
    * (Precondition: Airplane is initialized)
    */
    public static void cancelSeats(){
        
    }

    // 6
    /**
    * Prints passenger info
    * (Postcondition: Prints passenger info)
    * (Precondition: Airplane is initialized)
    */
    public static void printPassengerInfo(){
        System.out.format("%10s %10s | %4s\n", "Name", "", "Seat");
        for(int i = 0; i < passengers.size(); i++){
            System.out.format("%10s %10s | %2s%s\n",
                    passengers.get(i).getFirstName(),
                    passengers.get(i).getLastName(),
                    (char)(passengers.get(i).getRow() + 64),
                    passengers.get(i).getColumn());
        }
        System.out.println();
    }

    // 7
    /**
    * Prints reserved seats info
    * (Postcondition: Prints reserved seats info)
    * (Precondition: Airplane is initialized)
    */
    public static void printReservedSeatsInfo(){
        
    }

    // 8
    /**
    * Reserve seats by seat class and preferential seating
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void reserveSeatsSpecial(){
        
    }
}
