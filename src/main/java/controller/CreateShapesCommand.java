package controller;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.shapes.MakeShape;
import model.shapes.MyShapesList;
import java.awt.*;
import static model.shapes.ShapeFactory.createShape;

// create shape command class
// needs to know about app state, shape lists, start & end coords
// adds created shape to myshapelist
// responsibility is to create a shape
public class CreateShapesCommand implements ICommand, IUndoable{
    private MyShapesList myShapesList;
    private Point startC;
    private Point endC;
    public IShape iShape;

    private ShapeShadingType shadingType;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;

    // creates something to represent the shape
    public CreateShapesCommand(ShapeType st, ShapeShadingType sst, ShapeColor pC, ShapeColor sC, Point startC, Point endC, MyShapesList myShapesList){
        shadingType = sst;
        shapeType = st;
        primaryColor = pC;
        secondaryColor = sC;
        this.startC = startC;
        this.endC = endC;
        this.myShapesList = myShapesList;
    }

    @Override
    public void run() {
        MakeShape makeShape = new MakeShape(startC, endC, shapeType, shadingType, primaryColor, secondaryColor);
        iShape = createShape(makeShape);
        myShapesList.addShape(iShape); // save shape into myShapes list
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        myShapesList.removeShape();
    }

    @Override
    public void redo() {
        myShapesList.redoShape();
    }


}

