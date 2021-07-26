package controller;

import model.persistence.ApplicationState;
import model.interfaces.IShape;
import model.shapes.MyShapesList;

import java.awt.*;
import java.util.ArrayList;

public class SelectShapesCommand implements ICommand, IUndoable {

    ApplicationState applicationState;
    private MyShapesList myShapesList;
    private Point startC;
    private Point endC;
    public ArrayList<IShape> moveToSelected;

    public SelectShapesCommand(MyShapesList myShapesLists, Point startC, Point endC, ApplicationState applicationState) { // need to pass shape configs??
        this.applicationState = applicationState;
        this.myShapesList = myShapesLists;
        this.startC = startC;
        this.endC = endC;
    }
    // create invisible bounding box from start coord to end coords
    // determine if shape is in bounding box
    //  if yes, selected = true
//    public Boolean collision(IShape mouse, IShape shape){
//
//    }

    @Override
    public void run() {
//        System.out.println("running in ssc");
        myShapesList.clearSelected();
        moveToSelected = myShapesList.getMyShapeList();
        // find mouse bounding box
        int mouseMinX = Math.min((int) startC.getX(), (int) endC.getX());
        int mouseMinY = Math.min((int) startC.getY(), (int) endC.getY());
        int mouseBoundsWidth = Math.abs((int) startC.getX() - (int) endC.getX());
        int mouseBoundsHeight = Math.abs((int) startC.getY() - (int) endC.getY());
        // if in bounding box add selected shape(s) to selected shape list
        for (IShape iShape : moveToSelected) {
            int shapeMinX = (int) Math.min(iShape.getMadeShape().getStartC().getX(), iShape.getMadeShape().getEndC().getX() - 5);
            int shapeMinY = (int) Math.min(iShape.getMadeShape().getStartC().getY(), iShape.getMadeShape().getEndC().getY() - 5);
            int shapeWidth = iShape.getMadeShape().getWidth() + 10;
            int shapeHeight = iShape.getMadeShape().getHeight() + 10;
            // collision detection
            if (shapeMinX < mouseMinX + mouseBoundsWidth && shapeMinX + shapeWidth > mouseMinX
                    && shapeMinY < mouseMinY + mouseBoundsHeight && shapeMinY + shapeHeight > mouseMinY) {
                iShape.getMadeShape().shapeSelected = true;
                myShapesList.addToSelected(iShape);
//                if (iShape.getMadeShape().moved == false){ // initial shape drawn coords pushed to stack
//                Point s = iShape.getMadeShape().getStartC();
//                Point e = iShape.getMadeShape().getEndC();
//                myShapesList.movedUndoStartStack.push(s);
//                myShapesList.movedUndoEndStack.push(e);
//                }
            }
        }
        myShapesList.drawMyShapes();
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
