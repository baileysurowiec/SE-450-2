package model.shapes;
//new

import model.ShapeType;
import model.interfaces.IShape;

// here is where the create a shape is (new "shapetype")
    // called from CreateShapesCommand

public class ShapeFactory {
    public static IShape createShape(MakeShape makeShape){
        if(makeShape.shapeType.equals(ShapeType.RECTANGLE)){
            return new Rectangle(makeShape);
        }
        else if(makeShape.shapeType.equals(ShapeType.TRIANGLE)){
            return new Triangle(makeShape);
        }
        else if(makeShape.shapeType.equals(ShapeType.ELLIPSE)){
            return new Ellipse(makeShape);
        }
        else{
            System.out.println("Pick a shape to draw");
            return null;
        }
    }

}
