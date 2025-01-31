import processing.core.PApplet;
public class SudoBoard extends PApplet{

    // Member Variables
    private int xPos;
    private int yPos;
    private int boardSize;
    private int buttonSize;

    // Initialize a 2-Dimensional array to hold 9x9 SudoButtons
    static SudoButton [][] sudoButtonArray = new SudoButton[9][9];

    // Constructors
    public SudoBoard(int boardSize){
        this.boardSize = boardSize;
        xPos = 100; // Setting first button position at x = 100
        yPos = 100; // Setting first button position at y = 100
        buttonSize = boardSize / 15;

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sudoButtonArray[row][column] = new SudoButton(xPos + buttonSize * column, yPos + buttonSize * row, buttonSize, buttonSize, row, column);
            }
        }
    }

    public void display(PApplet pa){
        for(SudoButton [] row: sudoButtonArray){
            for(SudoButton thisSudoButton: row){
                thisSudoButton.display(pa);
            }
        }

        pa.fill(255,0,0);
        pa.textSize(25);
        pa.text("Solve Button will only \nwork when user has not \nentered any numbers onto \nthe board!!!",675,500);
    }
}
