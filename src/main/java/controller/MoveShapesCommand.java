package controller;

import model.interfaces.IShape;
import model.shapes.MyShapesList;
import java.awt.*;
import java.util.ArrayList;

public class MoveShapesCommand implements ICommand, IUndoable{
    private Point startC;
    private Point endC;
    private MyShapesList myShapesList;
    private ArrayList<IShape> moveSelected;

    public MoveShapesCommand(Point startC, Point endC, MyShapesList myShapesList){
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
            moveShape.getMadeShape().moved = true;
            int moveX = (int) offset.getX();
            int moveY = (int) offset.getY();
            Point s = moveShape.getMadeShape().getStartC();
            Point e = moveShape.getMadeShape().getEndC();
            // push original moved coords to stack for undo
            moveShape.getMadeShape().movedUndoStartStack.push(s);
            moveShape.getMadeShape().movedUndoEndStack.push(e);
            // create new points
            int xs = (int) s.getX() + moveX;
            int ys = (int) s.getY() + moveY;
            int xe = (int) e.getX() + moveX;
            int ye = (int) e.getY() + moveY;
            Point newStart = new Point(xs, ys);
            Point newEnd = new Point(xe, ye);
            // reset shape start/end to include moved offset
            moveShape.getMadeShape().setStartC(newStart);
            moveShape.getMadeShape().setEndC(newEnd);
        }
        myShapesList.drawMyShapes(); // redraw the shape list
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IShape undoShape: moveSelected){
            if( undoShape.getMadeShape().movedUndoStartStack.isEmpty() || undoShape.getMadeShape().movedUndoEndStack.isEmpty() ){
                System.out.println("Nothing to undo");
            }
            undoShape.getMadeShape().moved = false;

            Point start = undoShape.getMadeShape().movedUndoStartStack.pop(); // previous moved coords
            Point s = undoShape.getMadeShape().getStartC();
            undoShape.getMadeShape().movedRedoStartStack.push(s); // store current
            Point end = undoShape.getMadeShape().movedUndoEndStack.pop();
            Point e = undoShape.getMadeShape().getEndC();
            undoShape.getMadeShape().movedRedoEndStack.push(e);

            undoShape.getMadeShape().setStartC(start); // new coords set from prev
            undoShape.getMadeShape().setEndC(end);
        }
        myShapesList.drawMyShapes();
    }

    @Override
    public void redo() {
        for (IShape redoShape: moveSelected) {
            if(redoShape.getMadeShape().movedRedoStartStack.isEmpty() || redoShape.getMadeShape().movedRedoEndStack.isEmpty()){
                System.out.println("Nothing to redo");
            }
            redoShape.getMadeShape().moved = true;

            Point start = redoShape.getMadeShape().movedRedoStartStack.pop();
            Point s = redoShape.getMadeShape().getStartC();
            redoShape.getMadeShape().movedUndoStartStack.push(s);
            Point end = redoShape.getMadeShape().movedRedoEndStack.pop();
            Point e = redoShape.getMadeShape().getEndC();
            redoShape.getMadeShape().movedUndoEndStack.push(e);

            redoShape.getMadeShape().setStartC(start);
            redoShape.getMadeShape().setEndC(end);
        }
        myShapesList.drawMyShapes();
    }
}
