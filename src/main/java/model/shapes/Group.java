package model.shapes;

import model.interfaces.IShape;
import java.awt.*;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;


public class Group implements IShape{//}, INull {
    public ArrayList<IShape> groupedShapes; // children of group
    public ArrayList<IShape> deletedGroupShapes = new ArrayList<>();

    public ArrayList<ArrayList<IShape>> undoGroupedShapes;

    public int x;
    public int y;
    public int width;
    public int height;
    public Boolean groupSelected = false;

    public Group(ArrayList<IShape> group){
        groupedShapes = group;
        for(IShape shape : groupedShapes){ // set the shapes groups when a new group is made
            shape.getMadeShape().group = this;
//            shape.getMadeShape().addToGroupList(this); // add current group to each shapes group list
        }
    }

//    public void storeGroup(){
//        for(IShape shape : groupedShapes){
//            shape.getMadeShape().setShapeGroup();
//        }

//        for(IShape shape : groupedShapes){
//            shape.getMadeShape().addToGroupList(this);
//        }
//    }

//    public int getGroupSize(){
//        return groupedShapes.size();
//    }

    public void addShapeToGroup(IShape shape){
        groupedShapes.add(shape);
//        setGroupBounds();
    }

    public void removeShapeFromGroup(IShape shape){
        groupedShapes.remove(shape);
//        setGroupBounds();
    }



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
            moveGroupedShapes.addAll(groupedShapes);
        }
        else{
            groupSelected = false;
        }
    }


    public void deleteGroup(Group group){
        deletedGroupShapes.addAll(group.groupedShapes);
        groupedShapes.clear();
    }

    public void undoDelete(Group group){

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
        x = Math.min(x1, x2) ;//-10;
        y = Math.min(y1, y2) ;//-10;
        width = Math.abs(x1-x2) ;//+10;
        height = Math.abs(y1-y2) ;//+10;
    }

//    public Group getGroup(){
//        return this;
//    }


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
           shape = s.getMadeShape();
        }
        return shape;
    }

//    @Override
//    public Group2trial getGroup(){
//        return null;
//    }

    @Override
    public Boolean isGroup() {
        return true;
    }

    @Override
    public Boolean isSelected() {
        return null;
    }

//    @Override
//    public Boolean isNull() {
//        return false;
//    }
//
//    @Override
//    public int getGroupSize() {
//        return groupedShapes.size();
//    }
}
