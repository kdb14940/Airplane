/**
 * Airplane class
 * @author Addison Chan, Kyle Bascomb
 * @version 11/04/16
 */

public class Airplane {

    public final Seat[][] airplaneSeats;

    /**
    * Initializes an Airplane
    * (Postcondition: makes airplaneSeats)
    * (Precondition: none)
    */
    public Airplane (){
        airplaneSeats = new Seat[12][8];

        // initialize all of the seats
        for(int row = 0; row < airplaneSeats.length; row++){
            for(int column = 0; column < airplaneSeats[0].length; column++){
                boolean isWindow;
                boolean isAisle;
                boolean isMiddle;

                if(column == 1 || column == 8)
                    isWindow = true;
                else
                    isWindow = false;

                if(column == 2 || column == 3 || column == 6 || column == 7)
                    isAisle = true;
                else
                    isAisle = false;

                if(column <= 6 && column >= 3)
                    isMiddle = true;
                else
                    isMiddle = false;

                airplaneSeats[row][column] = new Seat(row + 1, column + 1, isWindow, isAisle, isMiddle);
            }
        }
    }
}
