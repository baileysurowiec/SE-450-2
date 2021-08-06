package controller;

import model.interfaces.IShape;
import java.awt.*;
import static model.shapes.MyShapesList.copyShapeList;
import static model.shapes.MyShapesList.selectedShapeList;

public class CopyShapesCommand implements ICommand{
    CopyShapesCommand(){}

    @Override
    public void run() {
        copyShapeList.clear();
        int offset = -50;
        for (IShape copyShape: selectedShapeList){
            // setting offset for copied shapes
            int xs = (int) copyShape.getMadeShape().getStartC().getX() + offset;
            int ys = (int) copyShape.getMadeShape().getStartC().getY() + offset;
            int xe = (int) copyShape.getMadeShape().getEndC().getX() + offset;
            int ye = (int) copyShape.getMadeShape().getEndC().getY() + offset;
            Point newStart = new Point(xs, ys);
            Point newEnd = new Point(xe, ye);
            copyShape.getMadeShape().setpasteStartC(newStart);
            copyShape.getMadeShape().setpasteEndC(newEnd);

            copyShapeList.add(copyShape);
        }
    }
}
