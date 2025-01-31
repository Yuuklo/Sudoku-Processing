// Back-End Algorithm for Sudoku
public class SudoAlgo {
    // Initialize a 2-Dimensional array of integers
    static int [][] sudoArray = new int[9][9];

    // Resets the board and fills it with values of 0
    public static void reset(){
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sudoArray[row][column] = 0;
            }
        }
    }

    public static boolean isSafe(int row, int col, int n){
        // Checks the rows
        for(int c = 0; c < 9; c++){
            if(sudoArray[row][c] == n){
                return false;
            }
        }

        // Checks the columns
        for(int r = 0; r < 9; r++){
            if(sudoArray[r][col] == n){
                return false;
            }
        }

        // Checks the 3x3 Region
        int startR = 3 * (row / 3);
        int startC = 3 * (col / 3);
        for (int r = startR; r < startR + 3 ; r++) {
            for (int c = startC; c < startC + 3 ; c++) {
                if(sudoArray[r][c] == n){
                    return false;
                }
            }
        }
        return true;
    }

    // Populates random indexes of sudoArray with random integers
    public static void populateBoard(){
        int cluesCount = 17;
        int cluesCounter = 0; // keeps track of the number of clues that have been placed on the board

        while (cluesCounter != cluesCount) {
            int row = (int)(Math.random() * 9);
            int column = (int)(Math.random() * 9);
            if (sudoArray[row][column] == 0) {
                sudoArray[row][column] = findSafeClue(row, column);
                cluesCounter++;
                SudoBoard.sudoButtonArray[row][column].setLocked(true);
            }
        }
    }


    // Finds an integer value that can be safely placed on the board
    public static int findSafeClue(int row, int column){
        boolean safeClueFound = false;

        while (!safeClueFound){
            int randomNumber = (int) (Math.random() * 10);

            if (isSafe(row,column,randomNumber)){
                return randomNumber;
            }
        }
        return 0;
    }

    // Returns the integer value of a position in sudoArray
    public static int getValue(int row, int column){
        return sudoArray[row][column];
    }

    // Sets the integer value of a position in sudoArray
    public static void setValue(int row, int column, String value){
        sudoArray[row][column] = Integer.parseInt(value);
    }

    // Displays the board (Not on the Screen)
    public static void displayBoard(){
        for (int row = 0; row < 9; row++) {
            int counter = 0;
            for (int column = 0; column < 9; column++) {
                System.out.print(sudoArray[row][column] + " ");
                counter++;
                if (counter % 3 == 0 && counter != 0 && column != 8){ // Adds vertical divider lines onto sudoArray
                    System.out.print(" │ ");
                }
            }
            System.out.println();
            if ((row+1) % 3 == 0 && row != 0 && row != 8)
                System.out.print("━━━━━━━━━━━━━━━━━━━━━━━\n"); // Adds horizontal divider lines onto sudoArray
        }
    }

    public static boolean sudoSolve(int row, int col) {
        if (row == 8 && col == 9)
            return true;
        else {
            // helps move to the next row if NOT the last spot on board
            if (col == 9) {
                row++;
                col = 0;
            }
            if (sudoArray[row][col] > 0) { // skips any preexisting values
                return sudoSolve(row, col + 1);
            }
            for (int number = 1; number <= 9; number++) {
                if (isSafe(row, col, number)) {
                    sudoArray[row][col] = number; // If number is safe, place number in index position

                    if (sudoSolve(row, col + 1)) { // After number is placed, call sudoSolve again to solve next col
                        return true; // Found a solution
                    }
                    sudoArray[row][col] = 0; // Backtrack if a solution is not found
                }
            }
            return false; // No valid number found, backtrack
        }
    }
}
