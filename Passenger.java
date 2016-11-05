/**
 * Initializes a Passenger for an Airplane
 * 
 * @author Addison Chan, Kyle Bascomb
 * @version 11/05/16
 */

public class Passenger
{
	private String firstName;
	private String lastName;
	private int row;
	private int column; // value 1-8 as a placeholder for the letters
	
	/**
	 * Initializes a passenger with a full name and a seat
	 * (PostCondition: Passenger will have a full name and values for a seat)
	 * @param firstName first name of the passenger
	 * @param lastName last name of the passenger
	 * @param row the seat row of the passenger's seat
	 * @param column the number value for the column of the passenger's seat
	 * (PreCondition: Both names are gives as a String and the seat values are integers
	 */
	public Passenger(String firstName, String lastName, int row, int column)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Returns the first name of the passenger
	 * (PostCondition: First name will be returned as a String)
	 * @return firstName the first name of the passenger
	 * (PreCondition: NA)
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
	 * @return row the passenger's seat row
	 * (PreCondtion: NA)
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Returns the column of the passenger's seat
	 * (PostCondition: seatColumn will be returned as an integer)
	 * @return seatColumn the passenger's seat column
	 * (PreCondtion: NA)
	 */ 
	public int getColumn()
	{
		return column;
	}
}
