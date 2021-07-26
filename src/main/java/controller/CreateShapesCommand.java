package controller;

import model.interfaces.IShape;
import model.persistence.ApplicationState;
import model.shapes.MakeShape;
import model.shapes.MyShapesList;
import model.shapes.ShapeFactory;

import java.awt.*;


// create shape command class
// needs to know about app state, shape lists, start & end coords
// adds created shape to myshapelist
// responsibility is to create a shape

public class CreateShapesCommand implements ICommand, IUndoable{

    ApplicationState applicationState;
    private MyShapesList myShapesList;
    private Point startC;
    private Point endC;
    public IShape iShape;

    // creates something to represent the shape
    public CreateShapesCommand(ApplicationState applicationState, Point startC, Point endC, MyShapesList myShapesList){
        this.applicationState = applicationState;
        this.startC = startC;
        this.endC = endC;
        this.myShapesList = myShapesList;
    }


    @Override
    public void run() {
        MakeShape makeShape = new MakeShape(applicationState, startC, endC);
        ShapeFactory shapeFactory = new ShapeFactory();
        iShape = shapeFactory.createShapeFromFactory(makeShape);
        myShapesList.addShape(iShape); // save shape into myShapes list

        CommandHistory.add(this);
//        System.out.println("run in createshapescommand");
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

