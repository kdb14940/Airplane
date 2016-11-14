
/**
 * Different ways of reserving seats on an airplane
 * 
 * @author Addison Chan, Kyle Bascomb
 * @version 11/05/16
 */
import java.util.Scanner;
import java.util.ArrayList;

public class ReserveSeats{
    /**
     * Reserve seats manually
     * (Postcondition: Reserves seats)
     * @param airplane the airplane
     * @param passengers the ArrayList of passengers
     * (Precondition: Airplane is initialized)
    */
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
            char columnChar;

            if (userChoice.length() == 2){
                row = Integer.parseInt(userChoice.substring(0,1));
                columnChar = userChoice.charAt(1);
            } else if (userChoice.length() == 3){
                row = Integer.parseInt(userChoice.substring(0,2));
                columnChar = userChoice.charAt(2);
            } else{
                throw new Exception("not valid input");
            }

            if('a' <= columnChar && columnChar <= 'h')
                columnChar = (char) (columnChar - 32);
            else if (columnChar < 'A' || 'H' < columnChar)
                throw new Exception("Not valid row");
            column = columnChar - 64;

            if (row > 12 || row < 1)
                throw new Exception("not valid input");

        } catch(Exception e) {
            System.out.println("That was not a valid input.");
            System.out.println("Your seat was not reserved.");
            System.out.println();
            return;
        }

        row -= 1; // off by one
        column -= 1; // off by one
        reserveSeats(airplane, passengers, firstName, lastName, row, column);

    }

    /**
    * Seats a person
    * (Postcondition: none)
    * @param airplane the airplane
    * @param passengers the ArrayList of passengers
	* @param firstName first name of the person
	* @param lastName last name of the person
	* @param row row of the seat, as stored
	* @param column column of the seat, as stored
    * (Precondition: row, column > 0, < 12 and 8. firstName and lastName are not empty strings)
    */
    public static void reserveSeats(Airplane airplane, ArrayList<Passenger> passengers, String firstName, String lastName, int row, int column){ // actual stored rows and columns
        if(airplane.getAirplaneSeats()[row][column].isVacant){
            passengers.add(new Passenger(firstName, lastName, row, column)); // adds new passenger to list of passengers
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
     * reserves a seat on an airplane
     * (PostCondition: if vacant, the seat will be reserved with the given name)
     * @param airplane the given airplane
     * @param passengers the arraylist of passengers on the plane
     * @param firstName first name of the passenger
     * @param lastName last name of the passenger
     * @param row actual stored row of the seat
     * @param column actual stored column of the seat
     * @param output boolean to check if reservation confirmation should be printed for the individual
     * (PreCondition: the airplane and passengers list is initialized)
     */
    public static void reserveSeats(Airplane airplane, ArrayList<Passenger> passengers, String firstName, String lastName, int row, int column, boolean output){ // actual stored rows and columns
        if(airplane.getAirplaneSeats()[row][column].isVacant){
            passengers.add(new Passenger(firstName, lastName, row, column)); // adds new passenger to list of passengers
            airplane.setAirplaneSeatName(row, column, firstName, lastName); // adds passenger to airplane (sets seat name)
            if(output){
                System.out.println("Your seat was reserved.\n");
            }
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
    * @param airplane the airplane
    * @param passengers the ArrayList of passengers
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
            case 1: // individual seating
                System.out.println("To smart-match seats, enter 1. (beta)");
                System.out.println("To choose options, enter 2.");
                System.out.print("> ");
                int individualChoice;
                try{
                    individualChoice = in.nextInt();
                    if(individualChoice < 1 || individualChoice > 2)
                        throw new Exception("Invalid number");
                } catch(Exception e){
                    System.out.println("That was not a valid choice.\n");
                    return;
                }
                if(individualChoice == 1)
                    individualSmartChoice(airplane, passengers);
                else if(individualChoice == 2)
                    individualPreferentialSeating(airplane, passengers);
                break;

            case 2: // group seating
                groupPreferentialSeating(airplane, passengers);
                break;

            default: // should never reach this case.
                return;
        }

    }

    /**
    * Allows user to type a sentence and picks a seat for them based on key words 
    * (Postcondition: Seat is reserved with given parameters)
	* @param airplane airplane that is being filled
	* @param passengers list of passengers on the plane
    * (Precondition: airplane is initialized)
    */
    public static void individualSmartChoice(Airplane airplane, ArrayList<Passenger> passengers){

        boolean[][] availableSeats = availableSeats(airplane); // rows = 12, columns = 8

        // 1 is yes, 0 is no, 2 is no preference
        int windowChoice = 2;
        int classChoice = 2;
        int middleChoice = 2;
        String firstName; // should never run into a case where
        String lastName; //  it's not initialized
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter your first name: ");
            firstName = in.nextLine();

            System.out.print("Please enter your last name: ");
            lastName = in.nextLine();
            System.out.println("Please enter your preferences, ie. I would like a first class window seat.");
            String prefs = in.nextLine().toLowerCase();
            // if(Pattern.matches("no", prefs) ||
            //         Pattern.matches("not", prefs)){
            //     System.out.println("'Not' and 'no' are unsupported. Sorry about that!");
            //     return;
            // }
            if(prefs.contains("window")){
                windowChoice = 1;
            } else if(prefs.contains("not window")) {
                windowChoice = 0;
            }
            if(prefs.contains("first class") ||
                    prefs.contains("not second class")){
                classChoice = 1;
            } else if(prefs.contains("second class") ||
                    prefs.contains("not first class")) {
                classChoice = 0;
                System.out.println("executed");
            } else {
                classChoice = 2;
            }
            if(prefs.contains("middle") ||
                    prefs.contains("not side")) {
                middleChoice = 1;
            } else if(prefs.contains("not middle") ||
                    prefs.contains("side")) {
                middleChoice = 1;
            }
        } catch (Exception e){
            System.out.println("That was not a valid option");
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
                    reserveSeats(airplane, passengers, firstName, lastName, row, column);
                    return;
                }
            }
            System.out.println();
        }
    }

    /**
     * Gets a seat based on individual preferences
     * (Postcondition: none)
     * @param airplane the airplane
     * @param passengers the ArrayList of passengers
     * (Precondition: none)
    */
    private static void individualPreferentialSeating(Airplane airplane, ArrayList<Passenger> passengers){

        Scanner in = new Scanner(System.in);
        boolean[][] availableSeats = availableSeats(airplane); // rows = 12, columns = 8

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
                    reserveSeats(airplane, passengers, firstName, lastName, row, column);
                    return;
                }
            }
            System.out.println();
        }

    }

    /**
     * Gets a seat based on group number
     * (Postcondition: none)
     * @param airplane the airplane
     * @param passengers the ArrayList of passengers
     * (Precondition: none)
    */
    public static void groupPreferentialSeating(Airplane airplane, ArrayList<Passenger> passengers){

        Scanner in = new Scanner(System.in);
        boolean[][] availableSeats = availableSeats(airplane); // rows = 12, columns = 8
        int numberOfSeats; // number of people in party
        int classChoice; // 0 is no preference, 1 is first class, 2 is second

        // get user input
        try{
            System.out.print("Please enter the number of people in your party: ");
            numberOfSeats = in.nextInt();
            if(numberOfSeats > 96) // if more than 2 rows
                throw new Exception("Exceeded max input");
            System.out.println("Would you like first class seats?");
            System.out.print("Enter 1 for yes, 0 for no, or 2 for no preference: ");
            classChoice = in.nextInt();
        } catch(Exception e){
            System.out.println("That was not valid input.");
            System.out.println("No seats were reserved.");
            return;
        }

        int[] seatsFound;
        if(classChoice == 1)
            seatsFound = findSeatsGroup(availableSeats, numberOfSeats, 0, 4);
        else if(classChoice == 0)
            seatsFound = findSeatsGroup(availableSeats, numberOfSeats, 4, 12);
        else
            seatsFound = findSeatsGroup(availableSeats, numberOfSeats, 0, 12);

        if(seatsFound[0] != -1){
            int row = seatsFound[0];
            int column = seatsFound[1];
            int direction = seatsFound[2];
            // System.out.format("%s %s", row, column);
            String[][] names = getGroupNames(numberOfSeats);
            for(int i = 0; i < numberOfSeats; i++){
                // System.out.format("%s %s\n", row, column);
                String firstName = names[0][i];
                String lastName = names[1][i];
                reserveSeats(airplane, passengers, firstName, lastName, row, column, false);
                System.out.format("%s's seat is %s%s.\n", firstName, row + 1, (char)(column+65));

                // row and column logic
                if(column == 7 && direction == 1){
                    row -= 1;
                    direction = -1;
                } else if (column == 0 && direction == -1){
                    row -= 1;
                    direction = 1;
                } else {
                    column += direction;
                }
            }
            System.out.println("Seats reserved.\n");
            return;
        } else {
            System.out.println("No seats found");
            return;
        }

        // check for all possibilities
        // for(int row = 0; row < 12; row++){
        //     for(int size = 1; size <= 8; size++){
        //         int numberOfRemainingSeats = numberOfSeats - size;
        //         int[] seatsFound = hasSeats(availableSeats, size);
        //         boolean[][] reservedSeats = new boolean[12][8];
        //         if(seatsFound[0] == 1){
        //             boolean[][] foundSeats = findSeatsNear(availableSeats,
        //                     seatsFound[2],
        //                     seatsFound[2] + size,
        //                     row,
        //                     numberOfRemainingSeats,
        //                     reservedSeats);
        //             // if(foundSeats.size != 0){
        //             //     return 
        //             // }
        //         }
        //     }
        // }
    }

    /**
    * Finds a group of seats that are open
    * (PostCondition: final row, final column and the direction will be returned in an array)
    * @param availableSeats vacancy of each seat
    * @param numberofSeats number of seats to fill in a group
    * @param rowStart the first row to check
    * @param rowEnd the last row to check
    * @return int[] array with the end row, end column, and direction of open seats
    * (PreCondition: 0 >= rowStart >= 12 , 0 >= rowStart >= 12)
    */
    public static int[] findSeatsGroup(boolean[][] availableSeats, int numberOfSeats, int rowStart, int rowEnd){
        int openSeats = 0;
        int endRow = -1;
        int endColumn = -1;
        boolean[][] seatsToReserve = new boolean[12][8];
        // down for odds, up evens
        for(int row = rowStart; row < rowEnd; row++){
            for(int column = 0; column < 8; column++){
                if(row % 2 == 0 && availableSeats[row][column]){
                    endRow = row;
                    endColumn = column;
                    openSeats++;
                    // System.out.format("down %s\n", openSeats);
                } else if(row % 2 == 1 && availableSeats[row][8 - column - 1]) {
                    endRow = row;
                    endColumn = 8 - column - 1;
                    openSeats++;
                    // System.out.format("up %s\n", openSeats);
                } else {
                    openSeats = 0;
                    endRow = -1;
                    endColumn = -1;
                    // System.out.format("other %s\n", openSeats);
                }
                if(openSeats == numberOfSeats){
                    int direction = (row % 2) * 2 - 1; // odd = positive, even = negative
                    return new int[]{endRow, endColumn, direction};
                }
            }
        }
        openSeats = 0;
        // System.out.format("transition %s\n", openSeats);
        // down for evens, up odds
        for(int row = rowStart; row < rowEnd; row++){
            for(int column = 0; column < 8; column++){
                if(row % 2 == 1 && availableSeats[row][column]){
                    endRow = row;
                    endColumn = column;
                    openSeats++;
                    // System.out.format("up %s\n", openSeats);
                } else if(row % 2 == 0 && availableSeats[row][8 - column - 1]) {
                    endRow = row;
                    endColumn = 8 - column - 1;
                    openSeats++;
                    // System.out.format("down %s\n", openSeats);
                } else {
                    openSeats = 0;
                    endRow = -1;
                    endColumn = -1;
                    // System.out.format("other %s\n", openSeats);
                }
                if(openSeats == numberOfSeats){
                    int direction = (row % 2) * 2 - 1; // odd = positive, even = negative
                    return new int[]{endRow, endColumn, direction*-1};
                }
            }
        }
        return new int[]{-1, -1, 0};
    }

    /**
    * Finds required length of seats together
    * (Postcondition: availableSeats and numberOfSeats > 0)
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
                return new int[]{1, row, column};
            }
        }
        return new int[]{0, 0, 0};
    }

    // same as hasSeats with the exception of stating a starting row
    /**
    * Finds required length of seats together
    * (Postcondition: availableSeats and numberOfSeats > 0)
    * @param availableSeats array of available seats
    * @param numberOfSeats the number of seats requested
    * @param startRow row to start at
    * @return seatsFound int[], 1/0 (true, false), row, column
    * (Precondition: returns an int[] of 1/0, row, column)
    */
    public static int[] hasSeats(boolean[][] availableSeats, int numberOfSeats, int startRow){
        for(int row = startRow; row < availableSeats.length; row++){
            eachColumn: for(int column = 0; column < availableSeats[0].length - numberOfSeats + 1; column++){
                for(int currentColumn = 0; currentColumn < numberOfSeats; currentColumn++){
                    if(! availableSeats[row][column + currentColumn])
                        continue eachColumn;
                }
                return new int[]{1, row, column};
            }
        }
        return new int[]{0, 0, 0};
    }

    // match seats in only one row
    /**
    * Finds required length of seats together
    * (Postcondition: boolean[][] and numberOfSeats > 0)
    * @param availableSeats array of available seats
    * @param numberOfSeats the number of seats requested
    * @param row row that is being checked
    * @param startColumn column to start the check at
    * @return seatsFound int[], 1/0 (true, false), row, column
    * (Precondition: returns an int[] of 1/0, row, column)
    */
    public static int[] hasSeats(boolean[][] availableSeats, int numberOfSeats, int row, int startColumn){
        if(startColumn + numberOfSeats < 12){
            eachColumn: for(int column = startColumn; column < availableSeats[0].length - numberOfSeats + 1; column++){
                for(int currentColumn = 0; currentColumn < numberOfSeats; currentColumn++){
                    if(! availableSeats[row][column + currentColumn])
                        continue eachColumn;
                }
                return new int[]{1, row, column};
            }
        return new int[]{0, 0, 0};
        }

        if(startColumn - numberOfSeats > 0){
            eachColumn: for(int column = startColumn; column < availableSeats[0].length - numberOfSeats + 1; column++){
                for(int currentColumn = 0; currentColumn < numberOfSeats; currentColumn++){
                    if(! availableSeats[row][column - currentColumn])
                        continue eachColumn;
                }
                return new int[]{1, row, column};
            }
        return new int[]{0, 0, 0};
        }
        return new int[]{0, 0, 0};
    }

    /**
    * Returns a boolean array of available seats on an Airplane
    * (Postcondition: returns a boolean array)
    * @param airplane the airplane
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
	
     /**
     * Returns a list of all passenger's names in a preferential seating group
     * (PreCondition: groupSize > 0)
     * @param groupSize number of passengers in a preferential seating group
     * @return groupNameList 2D array of the first and last names of the passengers
     * (PostCondition: The array will accomodate enough passengers for the group)
     */
    public static String[][] getGroupNames(int groupSize){
        Scanner in = new Scanner(System.in);
        String[][] groupNameList = new String [2][groupSize];//row 1: first name, row 2: last name
        for(int i = 0; i < groupSize; i++){
            System.out.format("Enter the first name of passenger %s: ", (i+1));
            groupNameList[0][i] = in.nextLine();
            System.out.format("Enter the last name of passenger %s: ", (i+1));
            groupNameList[1][i] = in.nextLine();
        }
        return groupNameList;
    }
}
