package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import view.gui.PaintCanvas;

import java.awt.*;

public class SelectedGroupDecorator implements IShape {
    private static PaintCanvas pc;
    Group group;

    public SelectedGroupDecorator(PaintCanvas pc, Group group){
        this.pc = pc;
        this.group = group;
    }

    @Override
    public void draw(Graphics g2d) {
        Graphics2D g = pc.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setColor(Color.RED);
        g.setStroke(stroke);
        int x = group.x-5;
        int y = group.y-5;
        int w = group.width + 10;
        int h = group.height + 10;

        g.drawRect(x, y, w, h);
    }

    @Override
    public MakeShape getMadeShape() {
        return null;
    }

    @Override
    public Boolean isGroup() {
        return null;
    }

    @Override
    public Boolean isSelected() {
        return null;
    }
}
