/**
 * Cancels seats by either seat number or name
 * 
 * @author Addison Chan, Kyle Bascomb
 * @version 11/05/16
 */
import java.util.ArrayList;
import java.util.Scanner;

public class CancelSeats {

    /**
    * Cancel seats
    * (Postcondition: Cancels seats)
    * @param airplane the airplane
    * @param passengers the ArrayList of passengers
    * (Precondition: Airplane is initialized and selection is either 1 or 2)
    */
    public static void cancelSeats(Airplane airplane, ArrayList<Passenger> passengers){
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
                    airplane.getAirplaneSeats()[row][column].isVacant = true;
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
            int row;//row of seat
            int column;//column of seat
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

                if (column > 12 || column < 1)
                    throw new Exception("not valid input");

            } catch(Exception e) {
                System.out.println("That was not a valid input.");
                System.out.println("No seat was cancelled.");
                System.out.println();
                return;
            }
            column--; // off by one error
            row--;
            airplane.getAirplaneSeats()[row][column].isVacant = true;

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

}
