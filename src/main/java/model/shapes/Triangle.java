package model.shapes;

import model.interfaces.IShape;
import java.awt.*;

import static model.shapes.MyShapesList.groupsList;
import static model.shapes.MyShapesList.myGroupsList;

public class Triangle implements IShape{
    MakeShape makeShape;
    TrianglePointsStrategy trianglePointsStrategy;
    private Boolean grouped = false;

    Triangle(MakeShape makeShape){ this.makeShape = makeShape; }

    public Triangle() { }

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
    public Boolean isSelected(){
        return makeShape.shapeSelected;
    }

//    @Override
//    public Group2trial getGroup(){
//        return null;
//    }

    @Override
    public MakeShape getMadeShape() {
        return makeShape;
    }

    @Override
    public Boolean isGroup() {
        return false;
//        int count = 0;
//        for(Group g : makeShape.groupsList){
//            for(Group g2 : makeShape.groupsList){
//                if(g.equals(g2)){
//                    count ++;
//                }
//            }
//        }
//        if(makeShape.groupsList.size() == count){
//            return false;
//        }
//        return true;
//        if(makeShape.uniqueGroups() == false){
//            return true;
//        }
//        return false;
//        for(Group group: myGroupsList){
//            if(group.getGroupedShapes().contains(this)){
//                return true;
//            }
//        }
//        return false;
    }

}
