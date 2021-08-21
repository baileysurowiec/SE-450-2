package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;
import static model.shapes.MyShapesList.drawMyShapes;

public class UnGroupShapesCommand implements ICommand, IUndoable{
    private ArrayList<ArrayList<Group>> groupSelection = new ArrayList<>();
    private ArrayList<ArrayList<Group>> ungroupSelection = new ArrayList<>();

    @Override
    public void run() {
        if (selectedGroups.isEmpty()) {
            System.out.println("no groups selected");
            return;
        }

        ArrayList<Group> undoGroups = new ArrayList<>();

        // look at each selected group
        for (Group sGroup : selectedGroups) {
            undoGroups.add(sGroup); // save selected group for undo (to re-group)
        }
        for (Group group : undoGroups) {
            // look at each shape with in group
            for (IShape s : group.getGroupedShapes()) { // get group's shape list
                MakeShape shape = s.getMadeShape();
                if (shape.groupsList.contains(group)) { // if the group is still in shape's group list, remove it
                    shape.groupsList.remove(group);
                    if (!shape.removedgroupList.contains(group)) { // add it to removed group for an undo
                        shape.removedgroupList.add(group);
                    }
                }
                if (!shape.groupsList.isEmpty()) {
                    Group g = shape.getLastGroup();
                    g.groupSelected = true;
                    selectedGroups.add(g);
                }
                if (shape.groupsList.isEmpty()) {
                    shape.shapeSelected = true;
                    selectedShapeList.add(s);
                }
            }
        }
        groupSelection.add(undoGroups);
        selectedGroups.removeAll(undoGroups);

        drawMyShapes();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if(groupSelection.isEmpty()){
            System.out.println("nothing to re-group");
            return;
        }

        ArrayList<Group> reGroup = groupSelection.remove(groupSelection.size()-1); // remove last group
        ungroupSelection.add(reGroup);

        for(Group group : reGroup){
            group.groupSelected = true;
            selectedGroups.add(group);

            for(IShape s : group.getGroupedShapes()){
                MakeShape shape = s.getMadeShape();
                shape.shapeSelected = false;
                selectedShapeList.remove(shape);
                if(shape.removedgroupList.contains(group)){
                    shape.removedgroupList.remove(group); // remove last group that was removed
                    if(!shape.groupsList.contains(group)){
                        shape.groupsList.add(group);

                    }
                }
            }
        }
        drawMyShapes();
    }

    @Override
    public void redo() {
        if(ungroupSelection.isEmpty()){
            System.out.println("nothing to ungroup");
            return;
        }

        ArrayList<Group> toUngroup = ungroupSelection.remove(ungroupSelection.size()-1);
        groupSelection.add(toUngroup);

        for(Group group : toUngroup){
            group.groupSelected = false;
            selectedGroups.remove(group);

            for(IShape s : group.getGroupedShapes()){
                MakeShape shape = s.getMadeShape();
                if(shape.groupsList.contains(group)){
                    shape.groupsList.remove(group); // remove last group that was removed
                    if(!shape.removedgroupList.contains(group)){
                        shape.removedgroupList.add(group);
                    }
                    if (!shape.groupsList.isEmpty()) {
                        Group g = shape.getLastGroup();
                        g.groupSelected = true;
                        selectedGroups.add(g);
                    }
                }
                if(shape.groupsList.isEmpty()){
                    shape.shapeSelected = true;
                    selectedShapeList.add(s);
                }
            }
        }
        drawMyShapes();
    }

}
