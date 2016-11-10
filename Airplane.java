/**
 * Airplane class
 * @author Addison Chan, Kyle Bascomb
 * @version 11/04/16
 */

public class Airplane {

    private Seat[][] airplaneSeats;

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

    /**
    * Returns airplaneSeats
    * (Postcondition: airplaneSeats is returned)
    * @return airplaneSeats seats of the airplane
    * (Precondition: airplaneSeats is initialized)
    */
    public Seat[][] getAirplaneSeats(){
        return airplaneSeats;
    }

    /**
    * Sets the name of a passenger
    * (Postcondition: seats names are set)
	* @param row row number of passenger
	* @param column column number of passenger
    * (Precondition: row, column >= 0 but smaller than 8 and 12)
    */
    public void setAirplaneSeatName(int row, int column, String firstName, String lastName){
        airplaneSeats[row][column].setPassengerName(firstName, lastName);
        airplaneSeats[row][column].isVacant = false;
    }
}
