package controller;

import model.interfaces.IShape;
import model.shapes.Group;
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
        ArrayList<Group> groups = new ArrayList<>();

        for (IShape shape : shapeList) {
            if(shape.isGroup()){
                Group sg = shape.getMadeShape().getLastGroup();
                if(!groups.contains(sg)){
                    groups.add(sg);
                }
            }
            else{
                shape.draw(g);
                if (shape.getMadeShape().shapeSelected) {
                    SelectedShapesDecorator ssd = new SelectedShapesDecorator(pc, shape);
                    ssd.draw(g);
                }
            }
        }
        for(Group group : groups){
            group.setGroupBounds();
            group.draw(g);
            if(group.groupSelected){
                SelectedShapesDecorator ssd = new SelectedShapesDecorator(pc, group);
                ssd.draw(g);
            }
        }
    }

}
