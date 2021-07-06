package model.interfaces;

import java.awt.*;

// each shape has primary color, secondary color, shading type, x,y coords of mouse click and of mouse release
// make the gets and sets go here

public interface IShape {
    public void getX1();
    public void getY1();
    public void getX2();
    public void getY2();


    void draw(Graphics g); // or graphics2D?

}
