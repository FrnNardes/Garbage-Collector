package model;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MemorySlot {
    private final Rectangle rect;
    private final Text counterTxt;
    private final Label code;
    private int counter = 0;


    public MemorySlot(Rectangle rect, Label code, Text counterTxt) {
        this.rect = rect;
        this.code = code;
        this.counterTxt = counterTxt;
    }

    public void setColor(Paint p){ 
        rect.setFill(p); 
    }

    public void setCounterText(){
        counterTxt.setText(String.valueOf(counter)); 
    }

    public void setCounter(int value){
        counter = value;
    }

    public int incrementCounter(){
        return counter += 1;
    }

    public int getCounter(){ 
        return Integer.parseInt(counterTxt.getText()); 
    }

    public String getCode(){
        return code.getText();
    }

    public Rectangle getRect(){
        return rect;
    }
}