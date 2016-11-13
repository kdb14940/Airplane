ReserveSeats.java ----

    /**
    * Finds seats before or after a given row
    * (Postcondition: the seat array will be populated if a match is found. If not, it will be blank)
    * (Precondition: )
    */
    public static boolean[][] findSeatsNear(boolean[][] availableSeats, int columnStart, int columnEnd, int row, int numberOfSeats, boolean[][] reservedSeats){
    // boolean[][] reservedSeats = new boolean[12][8];
        // find first sequence of blanks.
        int matchedSeats = 0;
        int newColumnStart = 0;
        int newColumnEnd = 0;

        for(int column = 0; column < 8; column++){ // CURRENTLY HERE
            if(availableSeats[row][column]){
                matchedSeats++;
            }
            if((!availableSeats[row][column] || (column == 8 && matchedSeats != 0)) &&
                    matchedSeats > 0 &&
                    (column - matchedSeats <= columnStart ||
                    column >= columnEnd)){
                reservedSeats[row][column] = false;
                numberOfSeats -= matchedSeats;
                if(numberOfSeats == 0)
                    return reservedSeats;
                else {
                    return findSeatsNear(availableSeats, column - matchedSeats, column, row++, numberOfSeats, reservedSeats);
                }
                // matchedSeats = 0;
            }
        }
        return new boolean[0][0];
    }
