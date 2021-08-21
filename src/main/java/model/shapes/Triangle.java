package model.shapes;

import model.interfaces.IShape;
import java.awt.*;

public class Triangle implements IShape{
    MakeShape makeShape;
    TrianglePointsStrategy trianglePointsStrategy;
    private Boolean grouped = false;

    Triangle(MakeShape makeShape){ this.makeShape = makeShape; }

    public void setTriangleStrategy(){
        int sX = (int) makeShape.getStartC().getX();
        int sY = (int) makeShape.getStartC().getY();
        int eX = (int) makeShape.getEndC().getX();
        int eY = (int) makeShape.getEndC().getY();

        if (sX < eX && sY> eY || sX > eX && sY < eY){
            this.trianglePointsStrategy = new RightTriangleStrategy();
        }
        else{
            this.trianglePointsStrategy = new LeftTriangleStrategy();
        }
        makeShape.setTrianglePointsStrategy(trianglePointsStrategy);
    }

    public TrianglePointsStrategy getTrianglePointsStrategy(){
        return trianglePointsStrategy;
    }

    @Override
    public void draw(Graphics graphics) {
        setTriangleStrategy();
        Graphics2D g = (Graphics2D) graphics;
        trianglePointsStrategy.drawTriangle(makeShape, g);
    }

    @Override
    public MakeShape getMadeShape() {
        return makeShape;
    }

    @Override
    public Boolean isGroup() {
        if(makeShape.groupsList.isEmpty()) {
            return false;
        }
        return true;
    }

}
