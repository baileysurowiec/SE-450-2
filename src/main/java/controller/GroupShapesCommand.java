package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;

import java.util.ArrayList;
import static model.shapes.MyShapesList.*;

public class GroupShapesCommand implements ICommand, IUndoable{
    private ArrayList<Group> newGroupUndoGroup = new ArrayList<>();
    private ArrayList<Group> newGroupRedoGroup = new ArrayList<>();

    @Override
    public void run() {
        if(selectedShapeList.size() <= 1 && selectedGroups.size() == 0 || selectedShapeList.size() == 0 && selectedGroups.size() <= 1) { return; }
        ArrayList<IShape> shapeList = new ArrayList<>();
        shapeList.addAll(selectedShapeList); // add selected shapes to shapeList
        selectedShapeList.clear(); // clear selected list

        ArrayList<IShape> combinedList = new ArrayList<>(); // list for all selected shape and all shapes from previous groups
        combinedList.addAll(shapeList);

        for(Group group: selectedGroups){
            for(IShape shape : group.getGroupedShapes()){
                combinedList.add(shape); // each shape from each group is added to the list of combined shapes
            }
        }
        selectedGroups.clear();

        // new group from selected shapes and grouped shapes
        Group newGroup = new Group(combinedList);
        newGroup.groupSelected = true;
        selectedGroups.add(newGroup);
        newGroupUndoGroup.add(newGroup); // save new group for undo

        drawMyShapes();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if(newGroupUndoGroup.isEmpty()){
            System.out.println("no groups to undo");
            return;
        }
        Group undoGroup = newGroupUndoGroup.remove(newGroupUndoGroup.size()-1); // remove last made group from newGroup list
        newGroupRedoGroup.add(undoGroup);

        for(IShape s : undoGroup.getGroupedShapes()){
            MakeShape shape = s.getMadeShape();
            if(shape.groupsList.contains(undoGroup)){
                shape.groupsList.remove(undoGroup);
                if(!shape.removedgroupList.contains(undoGroup)){ shape.removedgroupList.add(undoGroup); }
            }

            if(shape.groupsList.isEmpty()){ // shape is no longer part of a group
                shape.shapeSelected = true;
                selectedShapeList.add(s);
            }

            if(!shape.groupsList.isEmpty()){ // still more groups, select group
                Group g = shape.getLastGroup();
                g.groupSelected = true;
                selectedGroups.add(g);
            }
        }
        drawMyShapes();
    }

    @Override
    public void redo(){
        if(newGroupRedoGroup.isEmpty()){
            System.out.println("no groups to redo");
            return;
        }

        Group redoGroup = newGroupRedoGroup.remove(newGroupRedoGroup.size()-1); // remove last group from redone group list
        redoGroup.groupSelected = true;
        selectedGroups.add(redoGroup); // add group back to selected groups
        newGroupUndoGroup.add(redoGroup); // save for undo

        for(IShape s : redoGroup.getGroupedShapes()){
            MakeShape shape = s.getMadeShape();
            if(shape.removedgroupList.contains(redoGroup)){
                shape.removedgroupList.remove(redoGroup);
                if(!shape.groupsList.contains(redoGroup)){
                    shape.groupsList.add(redoGroup);
                    shape.shapeSelected = false; // unselect individual shape
                    selectedShapeList.remove(s);
                }
            }
        }
        drawMyShapes();
    }
}
