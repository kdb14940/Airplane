/**
 * Seat object
 * @author Addison Chan, Kyle Bascomb
 * @version 11/05/16
 */

public class Seat {

    public final int row;
    public final int column; // Position of seat as an integer
    public final boolean isWindow; // is it a window seat?
    public final boolean isAisle; // is it an aisle seat?
    public final boolean isMiddle; // either middle or side seat
    public boolean isVacant;
    private String firstName; // name of the passenger
    private String lastName;

    /**
    * Initializes a seat of an airplane
    * (Postcondition: a seat is initialized)
	* @param row row of the seat
	* @param letter letter of the seat
    * @param isWindow is window seat
    * @param isAisle is aisle seat
    * @param isMiddle is middle seat
    * (Precondition: row >= 1, letter is an actual letter)
    */
    public Seat (int row, int column, boolean isWindow, boolean isAisle, boolean isMiddle){
        this.row = row;

        // checker for column
        // checker will likely go unused because no one can edit this besides Airplane.java
        if (column >= 'a' && column <= 'z'){
            this.column = Character.toUpperCase(column);
        } else {
            this.column = column;
        }

        this.isWindow = isWindow;
        this.isAisle = isAisle;
        this.isMiddle = isMiddle;
        this.isVacant = true;
    }

    /**
    * Returns passenger first name
    * (Postcondition: returns passenger first name)
    * @return firstName
    * (Precondition: firstName is initialized and is set)
    */
    public String getFirstName(){
        return firstName;
    }

    /**
    * Returns passenger last name
    * (Postcondition: returns passenger last name)
    * @return lastName
    * (Precondition: lastName is initialized and is set)
    */
    public String getLastName(){
        return lastName;
    }

    /**
    * Sets passenger name
    * (Postcondition: none)
    * @param firstName first name of the passenger
    * @param lastName first name of the passenger
    * (Precondition: name exists)
    */
    public void setPassengerName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
