import processing.core.PApplet;
public class NumberPad extends NumberPadButton {

    // Member Variables
    private int xPos;
    private int yPos;
    private static int rowIndex;
    private static int colIndex;
    private int numberPadSize;
    private int buttonSize;

    // Initializing a 3x3 2-Dimensional array
    static NumberPadButton[][] numberPad = new NumberPadButton[3][3];

    public NumberPad(){
        // ...
    }

    public NumberPad(int numberPadSize){
        this.numberPadSize = numberPadSize;
        xPos = 750;
        yPos = 200;
        buttonSize = numberPadSize / 5;

        int counter = 1;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                String str = String.valueOf(counter); // finds the value of counter and converts it to a string var
                numberPad[row][column] = new NumberPadButton(xPos + buttonSize * column, yPos + buttonSize * row, buttonSize, buttonSize, str);
                counter++;
                rowIndex = row;
                colIndex = column;
            }
        }
    }

    // If user presses number on keypad, the number will be returned
    public static String getNumber(PApplet pa){
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (numberPad[row][column].isOverButton(pa)) {
                    return numberPad[row][column].getText();
                }
            }
        }
        return "";
    }

    public void display(PApplet pa){
        for(NumberPadButton[] row: numberPad){
            for(NumberPadButton thisNumberPadButton : row){
                thisNumberPadButton.display(pa);
            }
        }
        pa.textSize(20);
        pa.text("Enter numbers through your keypad!",650,150);
    }
}
