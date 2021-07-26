package controller;
import model.interfaces.IShape;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.util.ArrayList;

// draw method draws the shapes from the shape list
// knows about paint canvas & shape lists
public class DrawShapes{
    private ArrayList<IShape> myShapesList;
    private static PaintCanvas pc;

// this function is responsible for drawing the shape lists
    public DrawShapes(ArrayList<IShape> myShapesList, PaintCanvasBase pc){
        this.myShapesList = myShapesList;
        this.pc = (PaintCanvas) pc;
    }

    public void drawShapeLists(){
//        pc.repaint();
//        Graphics2D g = (Graphics2D) g2d;
        Graphics2D g = pc.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, pc.getWidth(), pc.getHeight());
        for (IShape shape : myShapesList){
            shape.draw(g);
            if (shape.getMadeShape().shapeSelected) {
                drawSelected(shape);
            }
        }
    }

    public void drawSelected(IShape shape){ // selected shape border
        Graphics2D g = pc.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setColor(Color.RED);
        g.setStroke(stroke);
        int x = (int) shape.getMadeShape().getMin().getX() -5;
        int y = (int) shape.getMadeShape().getMin().getY() -5;
        int w = shape.getMadeShape().getWidth() + 10;
        int h = shape.getMadeShape().getHeight() + 10;
        g.drawRect(x, y, w, h);
    }


}
