package model.shapes;

import controller.DrawShapes;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class MyShapesList {
    private static PaintCanvas pc;
    public ApplicationState applicationState;

    public ArrayList<IShape> myShapeList = new ArrayList<>();
    public ArrayList<IShape> selectedShapeList = new ArrayList<>();
    public ArrayList<IShape> deletedShapeList = new ArrayList<>(); // store for undo & redo

    public Stack<Point> movedUndoStartStack = new Stack<>();
    public Stack<Point> movedUndoEndStack = new Stack<>();
    public Stack<Point> movedRedoStartStack = new Stack<>();
    public Stack<Point> movedRedoEndStack = new Stack<>();

    public MyShapesList(ApplicationState applicationState, PaintCanvasBase pcb){
        this.applicationState = applicationState;
        this.pc = (PaintCanvas) pcb;
    }

    public void addShape(IShape iShape){
        myShapeList.add(iShape);
        deletedShapeList.clear();
        drawMyShapes(); // redraw with new shape
    }

    public void addToSelected(IShape iShape){
        selectedShapeList.add(iShape);
        drawMyShapes(); // redraw with new shape
    }

    public void removeShape(){
        if(myShapeList.size() == 0){
            System.out.println("Shape List is empty");
            return;
        }
        IShape removeShape = myShapeList.get(myShapeList.size()-1); // get last shape at index of size-1
        if(removeShape.getMadeShape().shapeSelected == true) {
            removeShape.getMadeShape().shapeSelected = false;
//            selectedShapeList.remove(selectedShapeList.size()-1);
        }
        myShapeList.remove(removeShape); // remove from shape list
        deletedShapeList.add(removeShape); // add to deleted shapes, store for redo
        drawMyShapes(); // redraw with updated shape list
    }
    public void clearSelected(){
        selectedShapeList.clear();
        for (IShape aShape : myShapeList){
            aShape.getMadeShape().shapeSelected = false;
        }
    }

    public void removeLastSelected(){
        if(selectedShapeList.size()==0){
            System.out.println("no selected shapes");
            return;
        }
        IShape removeSelected = selectedShapeList.get(selectedShapeList.size()-1); // last shape selected
        removeSelected.getMadeShape().shapeSelected = false;
        selectedShapeList.remove(removeSelected);
        deletedShapeList.add(removeSelected);
        drawMyShapes();
    }

    public void redoSelected(){
        if(deletedShapeList.size()==0){
           return;
        }
        IShape redo = deletedShapeList.get(deletedShapeList.size()-1);
        selectedShapeList.add(redo);
        redo.getMadeShape().shapeSelected = true;
        drawMyShapes();
    }

    public void redoShape(){
        if(myShapeList.size() != 0 && deletedShapeList.size() == 0){
            System.out.println("nothing to redo");
            return;
        }
        IShape redo = deletedShapeList.remove(deletedShapeList.size()-1);
        myShapeList.add(redo);
        drawMyShapes();
    }

    public void drawMyShapes(){
            DrawShapes drawShapes = new DrawShapes(myShapeList, pc);
            drawShapes.drawShapeLists();
    }

    public ArrayList<IShape> getMyShapeList() { return myShapeList; }
    public ArrayList<IShape> getDeletedShapeList() { return deletedShapeList; }
    public ArrayList<IShape> getSelectedShapeList() { return selectedShapeList; }

    }

