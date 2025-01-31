import processing.core.PApplet;
public class Button {

    // Member Variables
    private int xPos;
    private int yPos;
    private int wSize;
    private int hSize;

    // Constructors

    public Button(){
        // ...
    }
    public Button(int xPos, int yPos, int wSize, int hSize){
        this.xPos = xPos;
        this.yPos = yPos;
        this.wSize = wSize;
        this.hSize = hSize;
    }

    // Checks if the mouse cursor is over the Button object
    public boolean isOverButton(PApplet pa){
        if(pa.mouseX >= xPos - wSize/2 && pa.mouseX<=xPos+wSize/2 &&
                pa.mouseY >= yPos - hSize/2 && pa.mouseY<=yPos+hSize/2)
            return true;
        return false;
    }

    // Accessor Methods
    public int getXPos(){
        return xPos;
    }
    public int getYPos(){
        return yPos;
    }
    public int getWSize(){
        return wSize;
    }
    public int getHSize(){
        return hSize;
    }

    // Display Button
    public void display(PApplet pa){
        // If clicked, Button object will change color
        if (pa.mousePressed && isOverButton(pa))
            pa.fill(255,0,0,50);
        else
            pa.fill(255,255,255,50);

        pa.rect(xPos-(wSize/2), yPos-(hSize/2), wSize, hSize); // Creates rectangle shape
    }
}
