/**
 * Initializes a Passenger for an Airplane
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Passenger
{
	private String firstName;
	private String lastName;
	private int seatRow;
	private int seatColumn; // value 1-8 as a placeholder for the letters
	
	/**
	 * Initializes a passenger with a full name and a seat
	 * (PostCondition: Passenger will have a full name and values for a seat)
	 * @param fName first name of the passenger
	 * @parm lName last name of the passenger
	 * @param row the seat row of the passenger's seat
	 * @param column the number value for the column of the passenger's seat
	 * (PreCondition: Both names are gives as a String and the seat values are integers
	 */
	public Passenger(String fName, String lName, int row, int column)
	{
		firstName = fName;
		lastName = lName;
		seatRow = row;
		seatColumn = column;
	}
	
	/**
	 * Returns the first name of the passenger
	 * (PostCondition: First name will be returned as a String)
	 * @return firstName the first name of the passenger
	 * (PreCondtion: NA)
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Returns the last name of the passenger
	 * (PostCondition: last name will be returned as a String)
	 * @return lastName the last name of the passenger
	 * (PreCondtion: NA)
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Returns the row of the passenger's seat
	 * (PostCondition: seatRow will be returned as an integer)
	 * @return seatRow the passenger's seat row
	 * (PreCondtion: NA)
	 */
	public int getSeatRow()
	{
		return seatRow;
	}
	
	/**
	 * Returns the column of the passenger's seat
	 * (PostCondition: seatColumn will be returned as an integer)
	 * @return seatColumn the passenger's seat column
	 * (PreCondtion: NA)
	 */ 
	public int getSeatColumn()
	{
		return seatColumn;
	}
}
