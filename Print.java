/**
 * Prints lists for passenger info and reserved seats
 * 
 * @author Addison Chan, Kyle Bascomb
 * @version 11/05/16
 */
import java.util.ArrayList;

public class Print {
    /**
    * Prints passenger info
    * (Postcondition: Prints passenger info)
    * @param passengers the ArrayList of passengers
    * (Precondition: passengers is initialized)
    */
    public static void printPassengerInfo(ArrayList<Passenger> passengers){
        System.out.format("%10s %10s | %4s\n", "Name", "", "Seat");
        for(int i = 0; i < passengers.size(); i++){
            System.out.format("%10s %10s | %s%s\n",
                    passengers.get(i).getFirstName(),
                    passengers.get(i).getLastName(),
                    passengers.get(i).getRow() + 1,
                    (char)(passengers.get(i).getColumn() + 65));
        }
        System.out.println();
    }

    /**
    * Prints reserved seats info
    * (Postcondition: Prints reserved seats info)
    * @param airplane the airplane
    * (Precondition: Airplane is initialized)
    */
    public static void printReservedSeatsInfo(Airplane airplane){
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

}
