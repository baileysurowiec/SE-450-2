package model.shapes;

import controller.DrawShapes;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import java.util.ArrayList;

public class MyShapesList {
    private static PaintCanvas pc;
    public static ApplicationState applicationState;

    public static ArrayList<IShape> myShapeList = new ArrayList<>();
    public static ArrayList<IShape> selectedShapeList = new ArrayList<>();
    public static ArrayList<IShape> deletedShapeList = new ArrayList<>(); // store for undo & redo
    public static ArrayList<IShape> selectedUndoRedo = new ArrayList<>();
    public static ArrayList<IShape> copyShapeList = new ArrayList<>();
    public static ArrayList<ArrayList<IShape>> deletedSelection = new ArrayList<>();
    public static ArrayList<ArrayList<IShape>> deletedRedoSelection = new ArrayList<>();

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

    public static void removeShape(){
        if(myShapeList.size() == 0){
            System.out.println("Shape List is empty");
            return;
        }
        IShape removeShape = myShapeList.get(myShapeList.size()-1); // get last shape at index of size-1
        if(removeShape.getMadeShape().shapeSelected == true) {
            removeShape.getMadeShape().shapeSelected = false;
            selectedUndoRedo.add(removeShape);
            selectedShapeList.remove(removeShape);
        }
        myShapeList.remove(removeShape); // remove from shape list
        deletedShapeList.add(removeShape); // add to deleted shapes, store for redo
        drawMyShapes(); // redraw with updated shape list
    }

    public void clearSelected(){
        selectedShapeList.clear();
        selectedUndoRedo.clear();
        for (IShape aShape : myShapeList){
            aShape.getMadeShape().shapeSelected = false;
        }
    }

    public static void redoSelected(IShape redo){
        redo.getMadeShape().shapeSelected = true;
        selectedUndoRedo.remove(redo);
        selectedShapeList.add(redo);
        drawMyShapes();
    }

    public static void redoShape(){
        if(myShapeList.size() != 0 && deletedShapeList.size() == 0 || myShapeList.size()==0 && selectedUndoRedo.size()==0){
            System.out.println("list is empty");
            return;
        }
        IShape redo = deletedShapeList.remove(deletedShapeList.size() - 1);
        myShapeList.add(redo);
        if (selectedUndoRedo.contains(redo)){
            redoSelected(redo);
        }
        drawMyShapes();
    }

    public static void drawMyShapes(){
        DrawShapes drawShapes = new DrawShapes(myShapeList, pc);
        drawShapes.drawShapeLists();
    }

    public static ArrayList<IShape> getMyShapeList() { return myShapeList; }
    public ArrayList<IShape> getDeletedShapeList() { return deletedShapeList; }
    public ArrayList<IShape> getSelectedShapeList() { return selectedShapeList; }

    }

