package model.shapes;

import model.ShapeType;
import model.persistence.ApplicationState;

import java.awt.*;
import java.util.Stack;

// being a shape class:
// responsible for
//  shape configurations : colors, shape type, and shading type
//  dimensions : calculated here
//  point sets and gets here
public class MakeShape {
    public ShapeConfigs shapeConfigs;
    public Point startC;
    public Point endC;
    public Boolean shapeSelected = false;
    public Boolean moved = false;

    public Stack<Point> movedUndoStartStack = new Stack<>();
    public Stack<Point> movedUndoEndStack = new Stack<>();
    public Stack<Point> movedRedoStartStack = new Stack<>();
    public Stack<Point> movedRedoEndStack = new Stack<>();
    ApplicationState applicationState;

    public MakeShape(ApplicationState applicationState, Point startC, Point endC){
        this.applicationState = applicationState;
        this.shapeConfigs = applicationState.getShapeSettings();
        this.startC = startC;
        this.endC = endC;
    }

    public void draw(Graphics2D g){
        if(shapeConfigs.getShapeType().equals(ShapeType.RECTANGLE)){
            Rectangle rectangle = new Rectangle(this);
            rectangle.draw(g);
        }
        else if(shapeConfigs.getShapeType().equals(ShapeType.TRIANGLE)){
            Triangle triangle = new Triangle(this);
            triangle.draw(g);
        }
        else if(shapeConfigs.getShapeType().equals(ShapeType.ELLIPSE)){
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

    public void shapeSelected(){
        shapeSelected = !shapeSelected;
    }
    public boolean isSelected(){
        return shapeSelected;
    }

    public void setStartC(Point startC){
        this.startC.x = startC.x;
        this.startC.y = startC.y;
        movedUndoStartStack.push(this.startC);
    }
    public Point getStartC(){
        return startC;
    }
    public void setEndC(Point endC){
        this.endC.x = endC.x;
        this.endC.y = endC.y;
        movedUndoEndStack.push(this.endC);
    }
    public Point getEndC(){
        return endC;
    }


    public Stack<Point> getMovedUndoStartStack(){
        return movedUndoStartStack;
    }
    public Stack<Point> getMovedUndoEndStack(){
        return movedUndoEndStack;
    }
    public Stack<Point> getMovedRedoStartStack(){
        return movedRedoStartStack;
    }
    public Stack<Point> getMovedRedoEndStack(){
        return movedRedoEndStack;
    }
}
