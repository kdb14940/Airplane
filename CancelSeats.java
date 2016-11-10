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
