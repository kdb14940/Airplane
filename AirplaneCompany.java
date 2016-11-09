public class AirplaneCompany{

    /**
    * Reserve seats manually
    * (Postcondition: Reserves seats)
    * (Precondition: Airplane is initialized)
    */
    // TODO row and column are swapped!!
    public static void reserveSeatsManually(){
        String firstName;
        String lastName;
        int row;
        int column;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your first name: ");
        firstName = in.nextLine();
        System.out.print("Please enter your last name: ");
        lastName = in.nextLine();
        System.out.print("Please enter the seat you would like to reserve, ie. 1A: ");
        String userChoice = in.nextLine();

        // get input for seat
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
            System.out.println("Your seat was not reserved.");
            System.out.println();
            return;
        }

        row -= 1; // off by one
        column -= 1; // off by one
        reserveSeats(firstName, lastName, column, row);

    }
}
