package controller;

import model.ShapeType;
import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import model.shapes.TrianglePointsStrategy;
import view.gui.PaintCanvas;
import java.awt.*;

public class SelectedShapesDecorator implements IShape{

    private static PaintCanvas pcb;
    IShape shape;
    Group group;

    SelectedShapesDecorator(PaintCanvas pc, Group group){
        this.pcb = pc;
        this.group = group;
    }

    SelectedShapesDecorator(PaintCanvas pc, IShape shape){
        this.pcb = pc;
        this.shape = shape;
    }

    @Override
    public void draw(Graphics g2d) {
        Graphics2D g = pcb.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.RED);
        if(shape != null){
            if(!shape.isGroup()) {
                int x = (int) shape.getMadeShape().getMin().getX() - 5;
                int y = (int) shape.getMadeShape().getMin().getY() - 5;
                int w = shape.getMadeShape().getWidth() + 10;
                int h = shape.getMadeShape().getHeight() + 10;

                if (shape.getMadeShape().shapeType.equals(ShapeType.RECTANGLE)) {
                    g.drawRect(x, y, w, h);
                } else if (shape.getMadeShape().shapeType.equals(ShapeType.TRIANGLE)) {
                    TrianglePointsStrategy tps = shape.getMadeShape().getTrianglePointsStrategy();
                    tps.drawSelectedTriangle(shape, g);
                } else if (shape.getMadeShape().shapeType.equals(ShapeType.ELLIPSE)) {
                    g.drawOval(x, y, w, h);
                }
            }
        }
        else{
            int x = group.x-5;
            int y = group.y-5;
            int w = group.width + 10;
            int h = group.height + 10;

            g.drawRect(x, y, w, h);
        }
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
