package controller;

import model.persistence.ApplicationState;
import model.interfaces.IShape;
import model.shapes.MyShapesList;

import java.awt.*;
import java.util.ArrayList;

public class MoveShapesCommand implements ICommand, IUndoable{
    ApplicationState applicationState;
    private Point startC;
    private Point endC;
    private MyShapesList myShapesList;
    public ArrayList<IShape> moveSelected;

    public MoveShapesCommand(ApplicationState applicationState, Point startC, Point endC, MyShapesList myShapesList){
        this.applicationState = applicationState;
        this.startC = startC;
        this.endC = endC;
        this.myShapesList = myShapesList;
    }

    // offset: mouse released x,y - mouse pressed x,y (can be negative)
    public Point getOffset(){
        int moveX = (int) endC.getX() - (int) startC.getX(); // (-) is left (+) is right
        int moveY = (int) endC.getY() - (int) startC.getY(); // (-) is up (+) is down
        Point offset = new Point(moveX, moveY);
        return offset;
    }

    @Override
    public void run() {
        // get selected shape list
        if (myShapesList.selectedShapeList.isEmpty()){
            System.out.println("No shapes selected");
            return;
        }
        moveSelected = myShapesList.getSelectedShapeList();
        Point offset = getOffset();

        for (IShape moveShape: moveSelected) {
//            if (moveShape.getMadeShape().moved == false){ // initial shape drawn coords pushed to stack
//                Point s = moveShape.getMadeShape().getStartC();
//                Point e = moveShape.getMadeShape().getEndC();
//                myShapesList.movedUndoStartStack.push(s);
//                myShapesList.movedUndoEndStack.push(e);
//            }
            if (moveShape.getMadeShape().shapeSelected) {
                // shape has been moved at least once before, move shape and push coords to stack
                moveShape.getMadeShape().moved = true;
                int moveX = (int) offset.getX();
                int moveY = (int) offset.getY();
                int xs = (int) moveShape.getMadeShape().getStartC().getX() + moveX;
                int ys = (int) moveShape.getMadeShape().getStartC().getY() + moveY;
                int xe = (int) moveShape.getMadeShape().getEndC().getX() + moveX;
                int ye = (int) moveShape.getMadeShape().getEndC().getY() + moveY;
                Point newStart = new Point(xs, ys);
                Point newEnd = new Point(xe, ye);
                moveShape.getMadeShape().setStartC(newStart);
                moveShape.getMadeShape().setEndC(newEnd);

                // stack in MyShapesList
                myShapesList.movedUndoStartStack.push(newStart);
                myShapesList.movedUndoEndStack.push(newEnd);
            }
        }
        myShapesList.drawMyShapes(); // redraw the shape list
        CommandHistory.add(this);

    }

    @Override
    public void undo() {
//        System.out.println("undo in move");
        if(myShapesList.movedUndoStartStack.isEmpty() || myShapesList.movedUndoEndStack.isEmpty()){ System.out.println("Nothing to undo"); }
        for (IShape undoShape: myShapesList.getSelectedShapeList()){
//            if(undoShape.getMadeShape().movedUndoStartStack.isEmpty() || undoShape.getMadeShape().movedUndoEndStack.isEmpty()){
//                System.out.println("Nothing to undo");
//            }
            if (undoShape.getMadeShape().moved) {
//                undoShape.getMadeShape().moved = false;
                // stack in MakeShape
//               Point start = undoShape.getMadeShape().movedUndoStartStack.pop();
//               undoShape.getMadeShape().movedRedoStartStack.push(start);
//               Point end = undoShape.getMadeShape().movedUndoEndStack.pop();
//               undoShape.getMadeShape().movedRedoEndStack.push(end);

               // stack in MyShapesList
                Point start = myShapesList.movedUndoStartStack.pop();
                myShapesList.movedRedoStartStack.push(start);
                Point end = myShapesList.movedUndoEndStack.pop();
                myShapesList.movedRedoEndStack.push(end);
                undoShape.getMadeShape().setStartC(start);
                undoShape.getMadeShape().setEndC(end);
            }
        }
        myShapesList.drawMyShapes();
    }

    @Override
    public void redo() {
        if(myShapesList.movedRedoStartStack.isEmpty() || myShapesList.movedRedoEndStack.isEmpty()){ System.out.println("Nothing to redo"); }
//        System.out.println("redo in move");
        for (IShape redoShape: myShapesList.getSelectedShapeList()) {
//            if(redoShape.getMadeShape().movedRedoStartStack.isEmpty() || redoShape.getMadeShape().movedRedoEndStack.isEmpty()){
//                System.out.println("Nothing to redo");
//            }
            if (redoShape.getMadeShape().moved) {
//                redoShape.getMadeShape().moved = true;
                // stack in Make Shape
//                Point start = redoShape.getMadeShape().movedRedoStartStack.pop();
//                redoShape.getMadeShape().movedUndoStartStack.push(start);
//                Point end = redoShape.getMadeShape().movedRedoEndStack.pop();
//                redoShape.getMadeShape().movedUndoEndStack.push(end);

                // Stack in MyShapesList
                Point start = myShapesList.movedRedoStartStack.pop();
                myShapesList.movedUndoStartStack.push(start);
                Point end = myShapesList.movedRedoEndStack.pop();
                myShapesList.movedUndoEndStack.push(end);
                redoShape.getMadeShape().setStartC(start);
                redoShape.getMadeShape().setEndC(end);
            }
        }
        myShapesList.drawMyShapes();
    }
}
