package controller;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import java.awt.*;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;
import static model.shapes.ShapeFactory.createShape;

public class PasteShapesCommand implements ICommand, IUndoable{
    ArrayList<ArrayList<IShape>> redoPasteShapesList = new ArrayList<>();
    ArrayList<ArrayList<IShape>> pasteSelection = new ArrayList<>();
    ArrayList<Group> pastedGroups = new ArrayList<>();
    ArrayList<Group> redoPasteGroup = new ArrayList<>();

    @Override
    public void run() {
        if (copyShapeList.isEmpty() && copyGroupList.isEmpty()){
            System.out.println("No shapes copied");
            return;
        }
        ArrayList<IShape> newCopiedList = new ArrayList<>();
        for(IShape pasteShape : copyShapeList){
            MakeShape m = pasteShape.getMadeShape();
            Point startC = m.getPasteStartC();
            Point endC = m.getPasteEndC();
            ShapeType st = m.shapeType;
            ShapeShadingType sst = m.shadingType;
            ShapeColor pC = m.primaryColor;
            ShapeColor sC = m.secondaryColor;

            System.out.println(pC.toString());
                                // new pasteStart & end passed in here, original shape settings also passed in
            MakeShape makeShape = new MakeShape(startC, endC, st, sst, pC, sC );
            pasteShape = createShape(makeShape);

            newCopiedList.add(pasteShape);
            System.out.println(m.primaryColor.toString());
            myShapeList.add(pasteShape);
        }
        pasteSelection.add(newCopiedList);

        for(Group group : copyGroupList){
            ArrayList<IShape> newCopiedGroup = new ArrayList<>();
            for(IShape pasteShape : group.getGroupedShapes()){
                Point startC = pasteShape.getMadeShape().getPasteStartC();
                Point endC = pasteShape.getMadeShape().getPasteEndC();
                ShapeType st = pasteShape.getMadeShape().shapeType;
                ShapeShadingType sst = pasteShape.getMadeShape().shadingType;
                ShapeColor pC = pasteShape.getMadeShape().primaryColor;
                ShapeColor sC = pasteShape.getMadeShape().secondaryColor;

                MakeShape makeShape = new MakeShape(startC, endC, st, sst, pC, sC );
                pasteShape = createShape(makeShape);
                newCopiedGroup.add(pasteShape);
            }
            Group newGroup = new Group(newCopiedGroup);
            newGroup.setGroupBounds();
            myGroupsList.add(newGroup);
            pastedGroups.add(newGroup);
        }
        drawMyShapes();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if(!pasteSelection.isEmpty()){
           ArrayList<IShape> pastedShapes = pasteSelection.remove(pasteSelection.size()-1);
           for(IShape undo : pastedShapes){
               myShapeList.remove(undo);
           }
            redoPasteShapesList.add(pastedShapes);
            }
        if(!myGroupsList.isEmpty()){
            Group group = myGroupsList.remove(myGroupsList.size()-1);
            redoPasteGroup.add(group);
        }
        drawMyShapes();
    }

    @Override
    public void redo() {
        if(!redoPasteShapesList.isEmpty()) {
            ArrayList<IShape> redoShapeList = redoPasteShapesList.remove(redoPasteShapesList.size() - 1);
            for (IShape pasteShape : redoShapeList) { myShapeList.add(pasteShape); }
            pasteSelection.add(redoShapeList);
        }
        if(!redoPasteGroup.isEmpty()){
            Group group = redoPasteGroup.remove(redoPasteGroup.size()-1);
            myGroupsList.add(group);
        }
        drawMyShapes();
    }
}
