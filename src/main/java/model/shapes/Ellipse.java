package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShape;
import java.awt.*;

import static model.shapes.MyShapesList.groupsList;
import static model.shapes.MyShapesList.myGroupsList;

public class Ellipse implements IShape {
    MakeShape makeShape;
    private Color color;
    private Color secondaryColor;
    private Boolean grouped = false;
    Group group;

    Ellipse(MakeShape makeShape){
        this.makeShape = makeShape;
    }


    @Override
    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        color = makeShape.primaryColor.getColor();
        secondaryColor = makeShape.secondaryColor.getColor();
        int x = (int) makeShape.getMin().getX();
        int y = (int) makeShape.getMin().getY();
        int w = makeShape.getWidth();
        int h = makeShape.getHeight();


        if(makeShape.shadingType.equals(ShapeShadingType.OUTLINE)){
            g.setColor(color);
            g.setStroke(new BasicStroke(5));
            g.drawOval(x, y, w, h);
        }
        else if(makeShape.shadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(color);
            g.drawOval(x, y, w, h);
            g.fillOval(x, y, w, h);
        }
        else{
            g.setStroke(new BasicStroke(5));
            g.setColor(color);
            g.fillOval(x, y, w, h);
            g.setColor(secondaryColor);
            g.drawOval(x, y, w, h);
        }
    }

//    public Boolean isGrouped(){
//        for(Group group: myGroupsList){
//            if(group.getGroupedShapes().contains(this)){
//                return true;
//            }
//        }
//        return false;
////        return
////        return myGroupsList.contains(this);
//    }

    @Override
    public Boolean isSelected(){
        return makeShape.shapeSelected;
    }

    @Override
    public MakeShape getMadeShape() {
        return makeShape;
    }

//    @Override
//    public Group2trial getGroup(){
//        return null;
//    }

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
////        if(makeShape.uniqueGroups() == false){
//        return true;
//        }
//        return false;
    }

}
