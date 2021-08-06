package controller;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.shapes.MakeShape;
import java.awt.*;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;
import static model.shapes.ShapeFactory.createShape;

public class PasteShapesCommand implements ICommand, IUndoable{
    ArrayList<ArrayList<IShape>> redoPasteShapesList = new ArrayList<>();
    ArrayList<ArrayList<IShape>> pasteSelection = new ArrayList<>();

    PasteShapesCommand(){ }

    @Override
    public void run() {
        if (copyShapeList.isEmpty()){
            System.out.println("No shapes copied");
            return;
        }
        ArrayList<IShape> newCopiedList = new ArrayList<>();
        for(IShape pasteShape : copyShapeList){
            Point startC = pasteShape.getMadeShape().getPasteStartC();
            Point endC = pasteShape.getMadeShape().getPasteEndC();
            ShapeType st = pasteShape.getMadeShape().shapeType;
            ShapeShadingType sst = pasteShape.getMadeShape().shadingType;
            ShapeColor pC = pasteShape.getMadeShape().primaryColor;
            ShapeColor sC = pasteShape.getMadeShape().secondaryColor;
                                // new pasteStart & end passed in here, original shape settings also passed in
            MakeShape makeShape = new MakeShape(startC, endC, st, sst, pC, sC );
            pasteShape = createShape(makeShape);
            pasteShape.getMadeShape().pasted = true;
            newCopiedList.add(pasteShape);
        }
        myShapeList.addAll(newCopiedList);
        pasteSelection.add(newCopiedList);
        drawMyShapes();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if(!pasteSelection.isEmpty()){
           ArrayList<IShape> undoPaste = pasteSelection.remove(pasteSelection.size()-1);
           for(IShape undo : undoPaste){
               undo.getMadeShape().pasted = false;
               myShapeList.remove(undo);
           }
            redoPasteShapesList.add(undoPaste);
            drawMyShapes();
            }
    }

    @Override
    public void redo() {
        if(!redoPasteShapesList.isEmpty()) {
            ArrayList<IShape> redo = redoPasteShapesList.remove(redoPasteShapesList.size() - 1);
            for (IShape pasteShape : redo) {
                pasteShape.getMadeShape().pasted = true;
                myShapeList.add(pasteShape);
            }
            pasteSelection.add(redo);
            drawMyShapes();
        }
    }
}
