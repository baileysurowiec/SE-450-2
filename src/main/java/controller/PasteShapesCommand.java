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

    ArrayList<ArrayList<Group>> pastedGroups = new ArrayList<>();
    ArrayList<ArrayList<Group>> redoPasteGroup = new ArrayList<>();


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
                    // new pasteStart & end passed in here, original shape settings also passed in
            MakeShape makeShape = new MakeShape(startC, endC, st, sst, pC, sC );
            pasteShape = createShape(makeShape);

            newCopiedList.add(pasteShape);
            myShapeList.add(pasteShape);
        }
        pasteSelection.add(newCopiedList);

        ArrayList<Group> toPaste = new ArrayList<>();
        for(Group group : copyGroupList){
            ArrayList<IShape> newCopiedGroup = new ArrayList<>();
            for(IShape pasteShape : group.getGroupedShapes()){

                Point startC = pasteShape.getMadeShape().getPasteStartC();
                Point endC = pasteShape.getMadeShape().getPasteEndC();
                ShapeType st = pasteShape.getMadeShape().shapeType;
                ShapeShadingType sst = pasteShape.getMadeShape().shadingType;
                ShapeColor pC = pasteShape.getMadeShape().primaryColor;
                ShapeColor sC = pasteShape.getMadeShape().secondaryColor;
                ArrayList<Group> copyGroupList = pasteShape.getMadeShape().groupsList;
                ArrayList<Group> copyRemovedGroups = pasteShape.getMadeShape().removedgroupList;

                MakeShape makeShape = new MakeShape(startC, endC, st, sst, pC, sC );
                pasteShape = createShape(makeShape);

                pasteShape.getMadeShape().groupsList = new ArrayList<>(copyGroupList);
                pasteShape.getMadeShape().removedgroupList = new ArrayList<>(copyRemovedGroups);

                newCopiedGroup.add(pasteShape);
                myShapeList.add(pasteShape);
            }
            Group newGroup = new Group(newCopiedGroup);
            newGroup.setGroupBounds();

            toPaste.add(newGroup);
        }
        pastedGroups.add(toPaste);

        drawMyShapes();
        CommandHistory.add(this);
    }

    public ArrayList<Group> makeArrayListGroups(ArrayList<Group> groupsList, ArrayList<Group> newGroupsList){
        if(groupsList.isEmpty()){
            return newGroupsList;
        }
        for(Group group : groupsList){
            ArrayList<IShape> gs = group.getGroupedShapes();
            Group g = new Group(gs);
            g.getGroupedShapes().clear();

            for(IShape shape : group.getGroupedShapes()){
                IShape s = newShape(shape);
                g.getGroupedShapes().add(s);
            }
            newGroupsList.add(g);
        }

        for(Group group : newGroupsList){
            if(groupsList.contains(group)){ groupsList.remove(group); }
        }

        return makeArrayListGroups(groupsList, newGroupsList);

    }

    public IShape newShape(IShape pasteShape) {
        IShape shape = null;
        MakeShape m = pasteShape.getMadeShape();
        Point startC = m.getPasteStartC();
        Point endC = m.getPasteEndC();
        ShapeType st = m.shapeType;
        ShapeShadingType sst = m.shadingType;
        ShapeColor pC = m.primaryColor;
        ShapeColor sC = m.secondaryColor;
        // new pasteStart & end passed in here, original shape settings also passed in
        MakeShape makeShape = new MakeShape(startC, endC, st, sst, pC, sC);
        shape = createShape(makeShape);

        return shape;
    }

    @Override
    public void undo() {
        if(!pasteSelection.isEmpty()){
           ArrayList<IShape> pastedShapes = pasteSelection.remove(pasteSelection.size()-1);
           for(IShape undo : pastedShapes){ myShapeList.remove(undo); }
           redoPasteShapesList.add(pastedShapes);
        }

        if(!pastedGroups.isEmpty()){
            ArrayList<Group> undoPaste = pastedGroups.remove(pastedGroups.size()-1);
            redoPasteGroup.add(undoPaste);
            for(Group group : undoPaste) {
                for (IShape shape : group.getGroupedShapes()) { myShapeList.remove(shape); }
            }
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
            ArrayList<Group> redoPaste = redoPasteGroup.remove(redoPasteGroup.size()-1);
            pastedGroups.add(redoPaste);
            for(Group group : redoPaste) {
                for (IShape shape : group.getGroupedShapes()) {
                    myShapeList.add(shape);
                }
            }
        }
        drawMyShapes();
    }
}
