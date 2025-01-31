import processing.core.PApplet;
public class SudoButton extends Button{

    // Member Variables
    private static SudoButton selectedButton; // Keeps track of which button is currently being selected by user
    private boolean isSelected;
    private boolean isLocked;
    private static boolean numPadTriggered;
    private int rowIndex;
    private int colIndex;
    private String num;

    // Constructors
    public SudoButton(int xPos, int yPos, int wSize, int hSize, int rowIndex, int colIndex){
        super(xPos, yPos, wSize, hSize);
        isLocked = false;
        isSelected = false;
        numPadTriggered = false;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    // Mutators & Accessors
    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
    public boolean getIsSelected(){
        return isSelected;
    }
    public static void setNumPadTriggered(boolean trigger){
        numPadTriggered = trigger;
    }
    public static boolean getNumPadTriggered(){
        return numPadTriggered;
    }
    public void setNum(String num){
        this.num = num;
    }
    public void setLocked(boolean locked){
        this.isLocked = locked;
    }

    // Display SudoButton
    public void display(PApplet pa) {
        // Local variables to make code easier to read
        int xPos = getXPos();
        int yPos = getYPos();
        int wSize = getWSize();
        int hSize = getHSize();

        // If clicked, button will be selected and marked in Yellow (It will stay yellow until user clicks it again)
        if (pa.mousePressed && isOverButton(pa) && !isLocked) {
            pa.delay(100); // prevents mouse from flickering

            if (selectedButton != null && selectedButton != this) { // if there is a prev selectedButton or is not the same Button
                selectedButton.setIsSelected(false);
            }
            selectedButton = this;
            setIsSelected(true);
        }

        if (isSelected && !isLocked) {
            pa.fill(255, 255, 0, 50); // Colors it Yellow
            //System.out.println("SELECTED BUTTON: (" + rowIndex + ", " + colIndex + ")");
        } else if (isLocked) {
            pa.fill(225); // Grey color for locked buttons
        } else if (!isSelected) {
            pa.fill(255, 255, 255, 50); // Colors it back to White
        }
        pa.rect(xPos - (wSize / 2), yPos - (hSize / 2), wSize, hSize); // Creates rectangle shape

        int value = SudoAlgo.getValue(rowIndex, colIndex); // gets value from corresponding indexes

        // Display the clueButtons in the Sudoku board corresponding with sudoArray
        if (value != 0 && isLocked) {
            pa.fill(0);
            pa.textSize(50);
            pa.text(value, xPos - 13, yPos + 18);
        } else {
            isLocked = false;
        }

        // Display number that user inputs from numPad
        if (getNumPadTriggered() && isSelected) {
            pa.fill(0);
            pa.textSize(50);
            num = NumberPad.getNumber(pa); // Get the number from the NumberPad
            SudoAlgo.setValue(rowIndex, colIndex, num); // Updates values of sudoArray

            System.out.println(num + " <- This is the Num!");
            pa.text(num, xPos - 13, yPos + 18);
            setNumPadTriggered(false);
        }

        // Displays the solution onto the board when user presses Solve
        if (num != null) {
            pa.fill(0);
            pa.textSize(50);
            pa.text(num, xPos - 13, yPos + 18);
        }
    }


}
