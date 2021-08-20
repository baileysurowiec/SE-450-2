package model.shapes;

import model.ShapeType;
import model.interfaces.IShape;

// here is where the create a shape is (new "shape type")
    // called from CreateShapesCommand
// parameterized factory method
public class ShapeFactory {
    public static IShape createShape(MakeShape makeShape) {
        ShapeType shape = makeShape.shapeType;

        if(shape.equals(ShapeType.RECTANGLE)){
            return new Rectangle(makeShape);
        }
        else if(shape.equals(ShapeType.TRIANGLE)){
            return new Triangle(makeShape);
        }
        else{
            return new Ellipse(makeShape);
        }
    }
}
