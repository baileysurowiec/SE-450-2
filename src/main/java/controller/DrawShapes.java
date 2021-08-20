package controller;

import model.ShapeType;
import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.TrianglePointsStrategy;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;

// draw method draws the shapes from the shape list
// knows about paint canvas & shape lists
public class DrawShapes{
    private static PaintCanvas pc;

// this function is responsible for drawing the shape lists
    public DrawShapes(PaintCanvasBase pc){
        this.pc = (PaintCanvas) pc;
    }


    public void drawShapeLists(){
        ArrayList<IShape> shapeList = myShapeList;
        Graphics2D g = pc.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, pc.getWidth(), pc.getHeight());

//        SelectedShapesDecorator ssd = new SelectedShapesDecorator(pc);
        for (IShape shape : shapeList) {
            shape.draw(g);
            if (shape.getMadeShape().shapeSelected) {
//                ssd.drawSelected(shape);
//                drawSelected(shape);
                SelectedShapesDecorator ssd = new SelectedShapesDecorator(pc, shape);
                ssd.draw(g);
            }
        }

        for (Group shapeGroup : myGroupsList){
            shapeGroup.setGroupBounds();
            shapeGroup.draw(g);
            if (shapeGroup.groupSelected) {
//                ssd.drawSelected(shapeGroup);
//                drawSelected(shapeGroup);
                SelectedGroupDecorator sgd = new SelectedGroupDecorator(pc, shapeGroup);
                sgd.draw(g);
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

        if(shape.getMadeShape().shapeType.equals(ShapeType.RECTANGLE)){
            g.drawRect(x, y, w, h);
        }
        else if(shape.getMadeShape().shapeType.equals(ShapeType.TRIANGLE)){
            TrianglePointsStrategy tps = shape.getMadeShape().getTrianglePointsStrategy();
            tps.drawSelectedTriangle(shape, g);
        }
        else if(shape.getMadeShape().shapeType.equals(ShapeType.ELLIPSE)){
            g.drawOval(x, y, w, h);
        }
    }

    public void drawSelected(Group group){ // grouped shapes border
        Graphics2D g = pc.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.RED);
        int x = group.x-5;
        int y = group.y-5;
        int w = group.width + 10;
        int h = group.height + 10;

        g.drawRect(x, y, w, h);
    }
}
