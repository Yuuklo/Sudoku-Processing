import processing.core.PApplet;
public class Runner extends PApplet{

    // Global Variables
    static SudoBoard sudokuBoard;
    NumberPad numPad;
    FunctionButton solveButton;

    public static void main(String[] args) {
        PApplet.main("Runner", args);
        SudoAlgo.reset();
        SudoAlgo.populateBoard();

        while(true){
            SudoAlgo.displayBoard();
            System.out.println();
        }

    }

    public void settings(){
        size(1000,800);
        sudokuBoard = new SudoBoard(900);
        numPad = new NumberPad(300);
        solveButton = new FunctionButton(770,400,100,75,"Solve");
    }

    public void draw(){
        sudokuBoard.display(this);
        numPad.display(this);
        solveButton.display(this);
    }

    public void mouseReleased(){
        // Checks if user has pressed to Solve Sudoku
        if (solveButton.isOverButton(this)){
            SudoAlgo.sudoSolve(0,0);

            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    int value = SudoAlgo.getValue(row, column);
                    System.out.println(value);
                    sudokuBoard.sudoButtonArray[row][column].setNum(Integer.toString(value));
                }
            }
        }
    }
}
