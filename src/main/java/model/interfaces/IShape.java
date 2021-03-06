package model.interfaces;
import model.shapes.MakeShape;
import java.awt.*;

// any IShape object can draw & get the shape that was made
public interface IShape {
    void draw(Graphics g);
    MakeShape getMadeShape();
    Boolean isGroup();
}
