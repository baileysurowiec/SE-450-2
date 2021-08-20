package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import static model.shapes.MyShapesList.*;

// being a shape class:
// responsible for
//  shape configurations : colors, shape type, and shading type
//  dimensions : calculated here
//  point sets and gets here
public class MakeShape {//implements Cloneable{ //IPrototype {
    public Point startC;
    public Point endC;
    public Point pasteStartC;
    public Point pasteEndC;

    public Boolean shapeSelected = false;
    public Boolean grouped = false;

    public ShapeColor primaryColor;
    public ShapeColor secondaryColor;
    public ShapeType shapeType;
    public ShapeShadingType shadingType;

    public Stack<Point> movedUndoStartStack = new Stack<>();
    public Stack<Point> movedUndoEndStack = new Stack<>();
    public Stack<Point> movedRedoStartStack = new Stack<>();
    public Stack<Point> movedRedoEndStack = new Stack<>();

    public Stack<Group> groupHistory = new Stack<>();
    public Stack<Group> undoGroupHistory = new Stack<>();

//    public Group group;
    public ArrayList<Group> groupsList = new ArrayList<>();
    public ArrayList<Group> removedgroupList = new ArrayList<>();

    public Group group;

    public int soloShapeCount;

    TrianglePointsStrategy trianglePointsStrategy;
    public int numberOfGroups = 0;

    public MakeShape(Point startC, Point endC, ShapeType shapeType, ShapeShadingType shadingType, ShapeColor primaryColor, ShapeColor secondaryColor){
        this.startC = startC;
        this.endC = endC;
        this.shapeType = shapeType;
        this.shadingType = shadingType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public void draw(Graphics2D g){
        if(shapeType.equals(ShapeType.RECTANGLE)){
            Rectangle rectangle = new Rectangle(this);
            rectangle.draw(g);
        }
        else if(shapeType.equals(ShapeType.TRIANGLE)){
            Triangle triangle = new Triangle(this);
            triangle.draw(g);
        }
        else if (shapeType.equals(ShapeType.ELLIPSE)){
            Ellipse ellipse = new Ellipse(this);
            ellipse.draw(g);
        }
    }

//    public Group2trial getShapeGroup(){
//        return group;
//    }

    // find starting coords
    // need leftmost(least) X,Y coords for start of shape, rightmost(highest) for end of shape
    public Point getMin(){
        int sX = (int) Math.min(this.startC.getX(), this.endC.getX());
        int eY = (int) Math.min(this.startC.getY(), this.endC.getY());
        Point minC = new Point(sX, eY);
        return minC;
    }
    public Point getMax(){
        int sX = (int) Math.max(this.startC.getX(), this.endC.getX());
        int eY = (int) Math.max(this.startC.getY(), this.endC.getY());
        Point maxC = new Point(sX, eY);
        return maxC;
    }
    // WxH
    // height and width: subtract x1-x2, y1-y2 (no negative values)
    public int getWidth(){
        int width = (int) Math.abs(this.startC.getX()-this.endC.getX());
        return width;
    }
    public int getHeight(){
        int height = (int) Math.abs((this.startC.getY()-this.endC.getY()));
        return height;
    }

    public void setStartC(Point startC){ this.startC = startC; }
    public Point getStartC(){
        return startC;
    }
    public void setEndC(Point endC){ this.endC = endC; }
    public Point getEndC(){
        return endC;
    }

    public void setPasteStartC(Point s){
        pasteStartC = s;
    }
    public void setPasteEndC(Point e){ pasteEndC = e; }
    public Point getPasteStartC(){
        return pasteStartC;
    }
    public Point getPasteEndC(){
        return pasteEndC;
    }

    public TrianglePointsStrategy getTrianglePointsStrategy(){
        return trianglePointsStrategy;
    }
    public void setTrianglePointsStrategy(TrianglePointsStrategy tps){
        trianglePointsStrategy = tps;
    }

    public void increaseGroup(){ numberOfGroups++; }
    public void decreaseGroup(){ numberOfGroups--; }

//    public Boolean isGrouped(){
//        for(Group group: myGroupsList){
//            group.getGroupedShapes().contains(this);
//        }
////        return myGroupsList.contains(this);
//    }
//    public void setShapeGroup(){
////        groupsList.add(group);
//        if(groupsList.size() > 0){
//            soloShapeCount--;
//            this.group = groupsList.get(groupsList.size()-1);
//        }// sets the active group to the last one in the list
//        else{
//            soloShapeCount++;
//            group = null;
//        }
//    }

    public void addToGroupList(Group group){
//        if(!groupsList.isEmpty()) {
//            if (groupsList.contains(group)) {
//                grouped = false;
//            } else {
//                grouped = true;
//            }
//        }
        groupsList.add(group);
    }

//    public void undoGroup(){
//        if(!groupsList.isEmpty()) {
//            removedgroupList.add(groupsList.remove(groupsList.size() - 1));
////            soloShapeCount--;
//        }
//        setShapeGroup();
//    }

//    public void redoGroup(){
////        if(soloShapeCount == 0){
//        if(!removedgroupList.isEmpty()) {
//            groupsList.add(removedgroupList.remove(removedgroupList.size() - 1));
////            setShapeGroup();
//        }//}
//        setShapeGroup();
//    }

    public Boolean hasGroup(){
        return this.group != null;
    }

    public Boolean uniqueGroups(){
        for(Group g: groupsList){
            for(Group g2: groupsList){
                if(!g.equals(g2)){
                   return true;
                }
            }
        }
        return false;
    }

//    public int groupCount(){
//        int count = 0;
//        for(Group g: myGroupsList){
//            if(g.getGroupedShapes().contains(this)){
//                count++;
//            }
//        }
//        return count;
//    }

}
