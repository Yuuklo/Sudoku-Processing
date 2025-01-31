import processing.core.PApplet;
public class NumberPadButton extends Button{
    private String text;

    // Constructor
    public NumberPadButton(){
        // ...
    }

    public NumberPadButton(int xPos, int yPos, int wSize, int hSize, String text) {
        super(xPos, yPos, wSize, hSize);
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void display(PApplet pa){
        // Local variables to make code easier to read
        int xPos = getXPos();
        int yPos = getYPos();
        int wSize = getWSize();
        int hSize = getHSize();

        if (pa.mousePressed && isOverButton(pa)) {
            pa.fill(255, 0, 0);
        } else
            pa.fill(255,255,255);

        pa.rect(xPos-(wSize/2), yPos-(hSize/2), wSize, hSize);
        pa.textSize(hSize-15);
        pa.fill(0,0,0);
        pa.text(text,xPos-10,yPos+15);

        if (pa.mousePressed && isOverButton(pa)){
            SudoButton.setNumPadTriggered(true);
            System.out.println("NumPad Triggered: " + SudoButton.getNumPadTriggered());
        }
    }
}
