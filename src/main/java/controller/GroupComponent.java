package controller;

import model.interfaces.IShape;
import model.shapes.MakeShape;
import java.awt.*;

public class GroupComponent implements IComponent{

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public MakeShape getMadeShape() {
        return null;
    }

    @Override
    public Boolean isGroup() {
        return null;
    }
}
