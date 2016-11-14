/**
 * Displays the layout and occupancy of the airplane
 * 
 * @author Addison Chan, Kyle Bascomb
 * @version 11/05/16
 */
public class DisplayOccupancy{
   /**
   * Prints out a table of passengers
   * (Postcondition: Prints out table of passengers)
   * @param airplane the airplane
   * (Precondition: Airplane is initialized)
   */
    public static void displayOccupancy(Airplane airplane){

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
}

