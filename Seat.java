public class Seat {

    public final int row;
    public final int column; // letter of seat, always upper case
    public final boolean isWindow; // is it a window seat?
    public final boolean isAisle; // is it an aisle seat?
    public final boolean isMiddle; // either middle or side seat
    public boolean isVacant;
    private String passengerName; // name of the passenger

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

}
