package view.gui;

import controller.DrawShapes;
import model.interfaces.IShape;
import model.shapes.MyShapesList;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class PaintCanvas extends PaintCanvasBase {
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }



    @Override
    /**
     * This is an event handler.  If this function gets called, its time to
     * draw the entire picture.
     * It you want to force a paint event, call aPaintCanvas.repaint()
     */
    public void paint(Graphics g) {
        super.paint(g);
//        Graphics2D g2d = (Graphics2D) g;
//        for (IShape shape : myShapesList){
//            shape.draw(g);
//            if (shape.getMadeShape().shapeSelected) {
//                drawSelected(shape);
//            }
//        }
//        DrawShapes drawShapes = new DrawShapes(myShapeList, pc);


        System.out.println("Time to repaint");
    }

}
