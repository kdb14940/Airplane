import java.util.Scanner;
import java.util.ArrayList;

public class ReserveSeats{

    /**
    * Reserve seats manually
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    // TODO row and column are swapped!!
    public static void reserveSeatsManually(Airplane airplane, ArrayList<Passenger> passengers){
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
        reserveSeats(airplane, passengers, firstName, lastName, column, row);

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
    public static void reserveSeats(Airplane airplane, ArrayList<Passenger> passengers, String firstName, String lastName, int row, int column){ // actual stored rows and columns
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

    /**
    * Reserve seats with preferences
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void preferentialSeating(Airplane airplane, ArrayList<Passenger> passengers){
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

        boolean[][] availableSeats = availableSeats(airplane); // rows = 12, columns = 8

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

                    System.out.println("Would you like a first class seat?");
                    System.out.print("Enter 1 for yes, 0 for no, or 2 for no preference: ");
                    classChoice = in.nextInt();

                    System.out.println("Would you like a window seat?");
                    System.out.print("Enter 1 for yes, 0 for no, or 2 for no preference: ");
                    windowChoice = in.nextInt();

                    if(windowChoice != 1){
                        System.out.println("Would you like a side or middle seat?");
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
                            ReserveSeats.reserveSeats(airplane, passengers, firstName, lastName, row, column);
                            return;
                        }
                    }
                    System.out.println();
                }

                break;

            case 2: // group seating

                int numberOfSeats; // number of people in party

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

                if(numberOfSeats <= 8){ // can seat all in one row
                    int[] seatsFound = hasSeats(availableSeats, numberOfSeats);
                    if(seatsFound[0] == 1){
                        //reserve seats
                        //TODO
                        return;
                    } // else go try two rows
                }

                // try seating in two rows split evenly
                // PUT THIS INTO ONE METHOD 
                int firstHalf = (numberOfSeats + 1) / 2; // larger of the two
                int secondHalf = numberOfSeats / 2; // actually ok because 0.5 -> 0 and 1 -> 1
                int currentRow = 0;

                //while(true){
                //    int[] firstRowSeats = hasSeats(availableSeats, firstHalf, currentRow);
                //    if(firstRowSeats[0] == 0)
                //        break; // none found
                //    if(row != 0){
                //        for(int column = firstRowSeats[2]; column < firstRowSeats[2] + secondHalf; column++){
                //            if(! availableSeats[row-1][column])
                //                break;
                //        }
                //        return true;
                //    }
                //    if(row != 11){
                //        for(int column = firstRowSeats[2]; column < firstRowSeats[2] + secondHalf; column++){
                //            if(! availableSeats[row+1][column])
                //                break;
                //        }
                //        return true;
                //    }
                //    if(currentRow >= 12)
                //        break;
                //    break;
                //}
                //return false;
                ////TO HERE

                break;

            default: // should never reach this case.
                return;
        }

    }

    /**
    * Finds required length of seats together
    * (Postcondition: boolean[][] and numberOfSeats > 0)
    * @param availableSeats array of available seats
    * @param numberOfSeats the number of seats requested
    * @return seatsFound int[], 1/0 (true, false), row, column
    * (Precondition: returns an int[] of 1/0, row, column)
    */
    public static int[] hasSeats(boolean[][] availableSeats, int numberOfSeats){
        for(int row = 0; row < availableSeats.length; row++){
            eachColumn: for(int column = 0; column < availableSeats[0].length - numberOfSeats + 1; column++){
                for(int currentColumn = 0; currentColumn < numberOfSeats; currentColumn++){
                    if(! availableSeats[row][column + currentColumn])
                        continue eachColumn;
                }
                System.out.format("Found at %s %s \n", row, column);
                return new int[]{1, row, column};
            }
        }
        return new int[]{0, 0, 0};
    }

    // same as hasSeats with the exception of stating a starting row
    public static int[] hasSeats(boolean[][] availableSeats, int numberOfSeats, int startRow){
        for(int row = startRow; row < availableSeats.length; row++){
            eachColumn: for(int column = 0; column < availableSeats[0].length - numberOfSeats + 1; column++){
                for(int currentColumn = 0; currentColumn < numberOfSeats; currentColumn++){
                    if(! availableSeats[row][column + currentColumn])
                        continue eachColumn;
                }
                System.out.format("Found at %s %s \n", row, column);
                return new int[]{1, row, column};
            }
        }
        return new int[]{0, 0, 0};
    }

    /**
    * Returns a boolean array of available seats
    * (Postcondition: returns a boolean array)
    * (Precondition: none)
    */
    public static boolean[][] availableSeats(Airplane airplane){
        boolean[][] seats = new boolean[12][8];
        for(int row = 0; row < airplane.getAirplaneSeats().length; row++){
            for(int column = 0; column < airplane.getAirplaneSeats()[0].length; column++){
                seats[row][column] = airplane.getAirplaneSeats()[row][column].isVacant;
            }
        }
        return seats;
    }

}
