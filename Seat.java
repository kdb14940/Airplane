public class Seat {

    private int row;
    private char column; // letter of seat, always upper case

    /**
    * Initializes a seat of an airplane
    * (Postcondition: a seat is initialized)
	* @param row row of the seat
	* @param letter letter of the seat
    * (Precondition: row >= 1, letter is an actual letter)
    */
    public Seat (int row, char column){
        this.row = row;
        if (column >= 'a' && column <= 'z'){
            this.column = Character.toUpperCase(column);
        } else {
            this.column = column;
        }
    }

    /**
    * returns the column of the seat
    * (Postcondition: char >= A, char <= Z)
    * @return column column of the seat
    * (Precondition: none)
    */
    public char getColumn(){
        return column;
    }

    /**
    * returns the row of the seat
    * (Postcondition: int >= 1)
    * @return row row of the seat
    * (Precondition: none)
    */
    public int getRow(){
        return row;
    }
}
