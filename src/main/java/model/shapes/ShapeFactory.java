package model.shapes;
//new

import model.ShapeType;
import model.interfaces.IShape;

// here is where the create a shape is (new "shapetype")
    // called from CreateShapesCommand

public class ShapeFactory {

    public IShape createShapeFromFactory(MakeShape makeShape){
//        IShadingStrategy iShadingStrategy;
        IShape newShape = null;
        if(makeShape.shapeConfigs.getShapeType().equals(ShapeType.RECTANGLE)){
            newShape = new Rectangle(makeShape);
//            System.out.println("shape factory draw rectangle");
        }
        else if (makeShape.shapeConfigs.getShapeType().equals(ShapeType.TRIANGLE)){
            newShape = new Triangle(makeShape);
//            System.out.println("shape factory draw triangle");
        }
        else if(makeShape.shapeConfigs.getShapeType().equals(ShapeType.ELLIPSE)){
            newShape = new Ellipse(makeShape);
//            System.out.println("shape factory draw ellipse");
        }
        else{
            System.out.println("Pick a shape to draw");
        }
        return newShape;
    }

}
