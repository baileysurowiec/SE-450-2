package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import model.shapes.MyShapesList;
import java.awt.*;
import java.util.ArrayList;

import static model.shapes.MyShapesList.*;

public class MoveShapesCommand implements ICommand, IUndoable{
    private Point startC;
    private Point endC;
    private MyShapesList myShapesList;
    private ArrayList<IShape> moveSelected = new ArrayList<>();
    private ArrayList<Group> moveGroups = new ArrayList<>();

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

    public void moveShapes(IShape moveShape, Point offset){
        MakeShape m = moveShape.getMadeShape();
        int moveX = (int) offset.getX();
        int moveY = (int) offset.getY();
        Point s = m.getStartC();
        Point e = m.getEndC();
        // push original moved coords to stack for undo
        m.movedUndoStartStack.push(s);
        m.movedUndoEndStack.push(e);
        // create new points
        int xs = (int) s.getX() + moveX;
        int ys = (int) s.getY() + moveY;
        int xe = (int) e.getX() + moveX;
        int ye = (int) e.getY() + moveY;
        Point newStart = new Point(xs, ys);
        Point newEnd = new Point(xe, ye);
        // reset shape start/end to include moved offset
        m.setStartC(newStart);
        m.setEndC(newEnd);
    }

    public void undoMove(IShape shape){
        MakeShape m = shape.getMadeShape();
        if( m.movedUndoStartStack.isEmpty() || m.movedUndoEndStack.isEmpty() ){
            System.out.println("Nothing to undo");
        }
        Point start = m.movedUndoStartStack.pop(); // previous moved coords
        Point s = m.getStartC();
        m.movedRedoStartStack.push(s); // store current
        Point end = m.movedUndoEndStack.pop();
        Point e = m.getEndC();
        m.movedRedoEndStack.push(e);

        m.setStartC(start); // new coords set from prev
        m.setEndC(end);
    }

    public void redoMove(IShape shape){
        MakeShape m = shape.getMadeShape();
        if(m.movedRedoStartStack.isEmpty() || m.movedRedoEndStack.isEmpty()){
            System.out.println("Nothing to redo");
        }
        Point start = m.movedRedoStartStack.pop();
        Point s = m.getStartC();
        m.movedUndoStartStack.push(s);
        Point end = m.movedRedoEndStack.pop();
        Point e = m.getEndC();
        m.movedUndoEndStack.push(e);

        m.setStartC(start);
        m.setEndC(end);
    }

    @Override
    public void run() {
        if (selectedShapeList.isEmpty() && selectedGroups.isEmpty()){
            System.out.println("No shapes selected");
            return;
        }
        moveSelected.addAll(selectedShapeList);
        moveGroups.addAll(selectedGroups);

        Point offset = getOffset();

        for (IShape moveShape: moveSelected) { moveShapes(moveShape, offset); }

        ArrayList<IShape> moveGroupShapes = new ArrayList<>();
        for(Group group : moveGroups){
            for(IShape shape : group.getGroupedShapes()) {
                if (!moveGroupShapes.contains(shape)) {
                    moveGroupShapes.add(shape);
                }
            }
        }

        for(IShape shape: moveGroupShapes){ moveShapes(shape, offset); }

        drawMyShapes(); // redraw the shape list
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IShape undoShape: moveSelected){ undoMove(undoShape); }

        ArrayList<IShape> undoMoveGroup = new ArrayList<>();
        for(Group group : moveGroups){
            for(IShape shape : group.getGroupedShapes()) {
                if(!undoMoveGroup.contains(shape)){ undoMoveGroup.add(shape); }
            }
        }
        for(IShape s : undoMoveGroup){ undoMove(s); }

        drawMyShapes();
    }

    @Override
    public void redo() {
        for (IShape redoShape: moveSelected) { redoMove(redoShape); }

        ArrayList<IShape> redoMoveGroup = new ArrayList<>();

        for(Group group : moveGroups){
            for(IShape shape : group.getGroupedShapes()){
                if(!redoMoveGroup.contains(shape)){ redoMoveGroup.add(shape); }
            }
        }
        for(IShape s : redoMoveGroup){ redoMove(s); }

        drawMyShapes();
    }
}
