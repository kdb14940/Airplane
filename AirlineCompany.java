/**
 * Airplane Airlines simulator
 * @author Addison Chan, Kyle Bascomb
 * @version 11/04/16
 */

import java.util.Scanner;

public class AirlineCompany {
    private static Airplane airplane;

    public static void main (String [] args){
        airplane = new Airplane();
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Java Airlines.");
        while(true){
            System.out.println("Please select what you would like to do:");
            System.out.println("1. Display Passengers");
            System.out.println("2. Reserve seats manually");
            System.out.println("3. Reserve seats automatically");
            System.out.println("4. Preferential seating arrangement");
            System.out.println("5. Cancel seating by name or seat number");
            System.out.println("6. Print all passenger info");
            System.out.println("7. Print all reserved seats info");
            System.out.println("8. Reserve seats by seat class and preferential seating");
            System.out.println("0. Exit");
            System.out.print("> ");

            int choice = -1;
            try{
                choice = in.nextInt();
            } catch (Exception e){
                System.out.println("Not a choice");
                in.nextLine();
            }

            if (choice == 0)
                break; // kills loop

            switch (choice){
                case 1:
                    displayPassengers();
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
    * prints out a table of passengers
    * (Postcondition: Prints out table of passengers)
    * (Precondition: Airplane is initialized)
    */
    public static void displayPassengers(){
        
    }

    // 2
    /**
    * Reserve seats manually
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    public static void reserveSeatsManually(){
        
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
