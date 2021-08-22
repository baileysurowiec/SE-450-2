package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

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

    public ArrayList<Group> groupsList = new ArrayList<>();
    public ArrayList<Group> removedgroupList = new ArrayList<>();

    public Group group;

    TrianglePointsStrategy trianglePointsStrategy;

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


    public void addToGroupList(Group group){ groupsList.add(group); }

    public Group getLastGroup(){
        Group g = groupsList.get(groupsList.size()-1);
        return g;
    }

}
