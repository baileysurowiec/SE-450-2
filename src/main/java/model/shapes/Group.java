package model.shapes;

import model.interfaces.IShape;
import java.awt.*;
import java.util.ArrayList;

public class Group implements IShape{
    public ArrayList<IShape> groupedShapes; // children of group
    public int x;
    public int y;
    public int width;
    public int height;
    public Boolean groupSelected = false;

    public Group(ArrayList<IShape> group){
        groupedShapes = group;
        for(IShape shape : groupedShapes){ // set the shapes groups when a new group is made
            shape.getMadeShape().group = this;
            shape.getMadeShape().addToGroupList(this); // add current group to each shapes group list
        }
    }

    public void addShapeToGroup(IShape shape){ groupedShapes.add(shape); }
    public void removeShapeFromGroup(IShape shape){ groupedShapes.remove(shape); }

    public ArrayList<IShape> getGroupedShapes(){
        return groupedShapes;
    }

    public void selectGroup(int mx, int my, int mw, int mh){
        int gX = x -5;
        int gY = y -5;
        int gW = width +10;
        int gH = height +10;

        if (gX < mx + mw && gX + gW > mx && gY < my + mh && gY + gH > my) {
            groupSelected = true;
        }
        else{ groupSelected = false; }
    }

    public void setGroupBounds(){
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        for (IShape s : groupedShapes){
            MakeShape m = s.getMadeShape();
            int sx = (int) m.getMin().getX();
            int sy = (int) m.getMin().getY();
            int mx = (int) m.getMax().getX();
            int my = (int) m.getMax().getY();
            if (x1 == 0 || sx < x1){
                x1 = sx;
            }
            if (y1 == 0 || sy < y1){
                y1 = sy;
            }
            if (x2 == 0 || mx > x2){
                x2 = mx;
            }
            if (y2 == 0 || my > y2){
                y2 = my;
            }
        }
        x = Math.min(x1, x2);
        y = Math.min(y1, y2);
        width = Math.abs(x1-x2);
        height = Math.abs(y1-y2);
    }


    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (IShape s : groupedShapes){
            s.draw(g2d);
        }
    }

    @Override
    public MakeShape getMadeShape() {
        MakeShape shape = null;
        for (IShape s : groupedShapes){
           shape = s.getMadeShape(); }
        return shape;
    }

    @Override
    public Boolean isGroup() {
        return true;
    }

}
