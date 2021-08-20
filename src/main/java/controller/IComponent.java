package controller;

import model.shapes.MakeShape;
import java.awt.*;

public interface IComponent {
    void draw(Graphics g);
    MakeShape getMadeShape();
    Boolean isGroup();
}
