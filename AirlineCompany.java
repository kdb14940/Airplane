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

        System.out.println("Airplane Layout:");
        // System.out.print("   ");
        System.out.println("  1st Class       2nd Class");
        System.out.print(" ");
        for(int column = 0; column < airplane.getAirplaneSeats().length; column++){
            if(column == 4)
                System.out.print("    ");
            System.out.format("%3s", column + 1);
        }
        System.out.println();
        for(int column = 0; column < airplane.getAirplaneSeats()[0].length; column++){
            if(column == 2 || column == 6)
                System.out.println();
            System.out.print((char)(column + 65) + " ");
            for(int row = 0; row < airplane.getAirplaneSeats().length; row++){
                if(row == 4)
                    System.out.print("    ");
                if(airplane.getAirplaneSeats()[row][column].isVacant)
                    System.out.print("[ ]");
                else {
                    vacant--;
                    occupied++;
                    System.out.print("[x]");
                }
            }
            System.out.println();
        }
        System.out.format("Seats Vacant: %s | Seats Occupied: %s\n", vacant, occupied);
        System.out.println(); // to make newline before prompt again
    }

    // 2
    /**
    * Reserve seats manually
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    // TODO row and column are swapped!!
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
        System.out.print("Please enter the seat you would like to reserve, ie. 1A: ");
        String userChoice = in.nextLine();

        // get input for seat
        try {
            char rowChar;

            if (userChoice.length() == 2){
                column = Integer.parseInt(userChoice.substring(0,1));
                rowChar = userChoice.charAt(1);
            } else if (userChoice.length() == 3){
                column = Integer.parseInt(userChoice.substring(0,2));
                rowChar = userChoice.charAt(2);
            } else{
                throw new Exception("not valid input");
            }

            if('a' <= rowChar && rowChar <= 'h')
                rowChar = (char) (rowChar - 32);
            else if (rowChar < 'A' || 'H' < rowChar)
                throw new Exception("Not valid row");
            row = rowChar - 64;

            if (column > 12 || column < 1)
                throw new Exception("not valid input");

        } catch(Exception e) {
            System.out.println("That was not a valid input.");
            System.out.println("Your seat was not reserved.");
            System.out.println();
            return;
        }

        row -= 1; // off by one
        column -= 1; // off by one
        reserveSeats(firstName, lastName, column, row);

    }

    /**
    * Seats a person
    * (Postcondition: none)
	* @param firstName first name of the person
	* @param lastName last name of the person
	* @param row row of the seat, as stored
	* @param column column of the seat, as stored
    * (Precondition: row, column > 0, < 12 and 8. firstName and lastName are not empty strings)
    */
    public static void reserveSeats(String firstName, String lastName, int row, int column){ // actual stored rows and columns
        if(airplane.getAirplaneSeats()[row][column].isVacant){
            passengers.add(new Passenger(firstName, lastName, column, row)); // adds new passenger to list of passengers
            airplane.setAirplaneSeatName(row, column, firstName, lastName); // adds passenger to airplane (sets seat name)
            System.out.println("Your seat was reserved.\n");
        } else {
            System.out.println("The seat is already taken.");
            System.out.println("Your seat was not reserved.");
            System.out.println();
            return;
        }
    }

    // 3
    /**
    * Reserve seats automatically
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    // appears to be unnecessary
    public static void reserveSeatsAutomatically(){

    }

    // 4
    /**
    * Reserve seats with preferences
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void preferentialSeating(){
        Scanner in = new Scanner(System.in);
        int selection;

        System.out.println("To reserve seats for an individual, enter 1.");
        System.out.println("To reserve seats for a group, enter 2.");
        System.out.print("> ");

        try {
            selection = in.nextInt();
            if(selection != 1 && selection != 2)
                throw new Exception("Not a valid choice");
        } catch(Exception e) {
            System.out.println("That was not a valid choice.\n");
            return;
        } finally {
            in.nextLine(); // clear stdin \n character
        }

        boolean[][] availableSeats = availableSeats(); // rows = 12, columns = 8

        switch(selection){
            case 1:

                // 1 is yes, 0 is no, 2 is no preference
                int windowChoice = 2;
                int classChoice = 2;
                int middleChoice = 2;
                String firstName; // should never run into a case where
                String lastName; //  it's not initialized

                // get user input
                try{
                    System.out.print("Please enter your first name: ");
                    firstName = in.nextLine();

                    System.out.print("Please enter your last name: ");
                    lastName = in.nextLine();

                    System.out.println("Would you like first class seats?");
                    System.out.print("Enter 1 for yes, 0 for no, or 2 for no preference: ");
                    classChoice = in.nextInt();

                    System.out.println("Would you like window seats?");
                    System.out.print("Enter 1 for yes, 0 for no, or 2 for no preference: ");
                    windowChoice = in.nextInt();

                    if(windowChoice != 1){
                        System.out.println("Would you like side or middle seats?");
                        System.out.print("Enter 1 for middle, 0 for side, or 2 for no preference: ");
                        middleChoice = in.nextInt();
                    }
                } catch(Exception e) {
                    System.out.println("That was not valid input");
                    return;
                }

                // find a seat based on user choices // Individual seating
                if(windowChoice == 1){
                    for(int column = 1; column <= 6; column++){
                        for(int row = 0; row < availableSeats.length; row++){
                            availableSeats[row][column] = false;
                        }
                    }
                } else if(windowChoice == 0) {
                    for(int row = 0; row < availableSeats.length; row++){
                        availableSeats[row][0] = false;
                        availableSeats[row][7] = false;
                    }
                } // 2 do nothing

                if(classChoice == 0){ // second class
                    for(int row = 0; row < 4; row++){
                        for(int column = 0; column < availableSeats[0].length; column++){
                            availableSeats[row][column] = false;
                        }
                    }
                } else if(classChoice == 1) { // first class
                    for(int row = 4; row < availableSeats.length; row++){
                        for(int column = 0; column < availableSeats[0].length; column++){
                            availableSeats[row][column] = false;
                        }
                    }
                } // 2 do nothing

                if(middleChoice == 0){ // side seats
                    for(int row = 0; row < availableSeats.length; row++){
                        availableSeats[row][2] = false;
                        availableSeats[row][3] = false;
                        availableSeats[row][4] = false;
                        availableSeats[row][5] = false;
                    }
                } else if(middleChoice == 1) { // middle seats
                    for(int row = 0; row < availableSeats.length; row++){
                        availableSeats[row][0] = false;
                        availableSeats[row][1] = false;
                        availableSeats[row][6] = false;
                        availableSeats[row][7] = false;
                    }
                } // else do nothing

                for(int column = 0; column < availableSeats[0].length; column++){
                    for(int row = 0; row < availableSeats.length; row++){

                        // if(availableSeats[row][column]) // used for testing purposes
                        //     System.out.print("[ ]");
                        // else
                        //     System.out.print("[x]");

                        if (availableSeats[row][column]){
                            reserveSeats(firstName, lastName, row, column);
                            return;
                        }
                    }
                    System.out.println();
                }

                break;

            case 2: // group seating

                int numberOfSeats;

                // get user input
                try{
                    System.out.print("Please enter the number of people in your party: ");
                    numberOfSeats = in.nextInt();
                    if(numberOfSeats > 16) // if more than 2 rows
                        throw new Exception("Exceeded max input");
                } catch(Exception e){
                    System.out.println("That was not valid input.");
                    System.out.println("No seats were reserved.");
                    return;
                }

                // first match one row then match two rows
                break;
            default: // should never reach this case.
                return;
        }

    }

    /**
    * Returns a boolean array of available seats
    * (Postcondition: returns a boolean array)
    * (Precondition: none)
    */
    public static boolean[][] availableSeats(){
        boolean[][] seats = new boolean[12][8];
        for(int row = 0; row < airplane.getAirplaneSeats().length; row++){
            for(int column = 0; column < airplane.getAirplaneSeats()[0].length; column++){
                seats[row][column] = airplane.getAirplaneSeats()[row][column].isVacant;
            }
        }
        return seats;
    }

    // 5
       /**
    * Cancel seats
    * (Postcondition: Cancels seats)
    * (Precondition: Airplane is initialized and selection is either 1 or 2)
    */
    public static void cancelSeats(){
        System.out.println("To cancel a seat by name, Enter 1");
        System.out.println("To cancel a seat by seat number, Enter 2");
        System.out.print("> ");
        Scanner input = new Scanner(System.in);
        int selection;
        try {
            selection = input.nextInt();
            if(selection != 1 && selection != 2)
                throw new Exception("Not a valid choice");
        } catch(Exception e) {
            System.out.println("That was not a valid choice.\n");
            return;
        }

        // Cancellation for name
        if (selection == 1){
        	Scanner nameIn = new Scanner(System.in);
        	System.out.print("Enter your first name: ");
        	String firstName = nameIn.nextLine();
        	System.out.print("Enter your last name: ");
        	String lastName = nameIn.nextLine();

        	for (int i = 0; i < passengers.size(); i++){
        		Passenger temp = passengers.get(i);
                if(temp.getFirstName().equalsIgnoreCase(firstName) && temp.getLastName().equalsIgnoreCase(lastName)){
                    int column = temp.getColumn();
                    int row = temp.getRow();
                    airplane.getAirplaneSeats()[column][row].isVacant = true;
                    passengers.remove(i);// removes passenger from the list
                    System.out.println("Your seat has been successfully canceled");
                    System.out.println();
                    return;
                }
            }
            // if reaches this point, then there was no match
            System.out.println("There is no passenger with that name.");
            System.out.println();
            return;
        }

        //cancellation for seat number
        else if(selection == 2){
            Scanner seatIn = new Scanner(System.in);
            System.out.print("Enter your seat number: ");
            String userChoice = seatIn.nextLine();
            int column;//column of seat
            int row;//row of seat
            try {
                char rowChar;

                if (userChoice.length() == 2){
                    column = Integer.parseInt(userChoice.substring(0,1));
                    rowChar = userChoice.charAt(1);
                } else if (userChoice.length() == 3){
                    column = Integer.parseInt(userChoice.substring(0,2));
                    rowChar = userChoice.charAt(2);
                } else{
                    throw new Exception("not valid input");
                }

                if('a' <= rowChar && rowChar <= 'h')
                    rowChar = (char) (rowChar - 32);
                else if (rowChar < 'A' || 'H' < rowChar)
                    throw new Exception("Not valid row");
                row = rowChar - 64;

                if (column > 12 || column < 1)
                    throw new Exception("not valid input");

            } catch(Exception e) {
                System.out.println("That was not a valid input.");
                System.out.println("No seat was cancelled.");
                System.out.println();
                return;
            }
            column--; // off by one error
            row--; // off by one error 
            airplane.getAirplaneSeats()[column][row].isVacant = true;

            for (int i = 0; i < passengers.size(); i++){
                Passenger temp = passengers.get(i);
                if (temp.getRow() == row && temp.getColumn() == column){
                    passengers.remove(i); // removes the passenger from the list
                    System.out.println("Your seat has been successfully cancelled");
                    System.out.println();
                    return;
                }
            }
            System.out.println("The seat " + userChoice + " is already vacant");
            System.out.println();
            return;
        }
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
            System.out.format("%10s %10s | %s%s\n",
                    passengers.get(i).getFirstName(),
                    passengers.get(i).getLastName(),
                    passengers.get(i).getColumn() + 1,
                    (char)(passengers.get(i).getRow() + 65));
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
        System.out.format("%4s | %s\n", "Seat", "Name");
        for(int row = 0; row < airplane.getAirplaneSeats().length; row++){
            for(int column = 0; column < airplane.getAirplaneSeats()[0].length; column++){
                if(!airplane.getAirplaneSeats()[row][column].isVacant)
                    System.out.format("%3s%1s | %s %s\n",
                            row + 1,
                            (char)(column+65),
                            airplane.getAirplaneSeats()[row][column].getFirstName(),
                            airplane.getAirplaneSeats()[row][column].getLastName());
            }
        }
        System.out.println();
    }

    // 8
    /**
    * Reserve seats by seat class and preferential seating
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void reserveSeatsSpecial(){

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
