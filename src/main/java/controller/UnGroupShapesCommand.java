package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import java.util.ArrayList;

import static controller.GroupShapesCommand.originalGroup;
import static controller.GroupShapesCommand.redoGroups;
import static model.shapes.MyShapesList.*;
import static model.shapes.MyShapesList.drawMyShapes;

public class UnGroupShapesCommand implements ICommand, IUndoable{
    private ArrayList<Group> undoGroup = new ArrayList<>();
    private ArrayList<Group> redoGroup = new ArrayList<>();
    private ArrayList<ArrayList<IShape>> redoShapes = new ArrayList<>();
    private ArrayList<ArrayList<Group>> groupSelection = new ArrayList<>();
    private ArrayList<ArrayList<Group>> ungroupSelection = new ArrayList<>();

//    private ArrayList<ArrayList<Group>> originalGroup = new ArrayList<>(); // grouped saved for later
//    private ArrayList<ArrayList<Group>> redoGroups = new ArrayList<>();

    @Override
    public void run() {

        if(!selectedGroups.isEmpty()) {
            ArrayList<Group> ungroupSelection = new ArrayList<>();
            for (Group group : selectedGroups) {
                // remove group from groups list
                myGroupsList.remove(group);
                ungroupSelection.add(group); // save selected groups as it

                // for each group, check group history
                for(IShape shape : group.getGroupedShapes()){
                    shape.getMadeShape().groupHistory.push(group);

                    if (!shape.getMadeShape().undoGroupHistory.isEmpty()) {
                        System.out.println(shape.getMadeShape().undoGroupHistory.size());
                        Group g = shape.getMadeShape().undoGroupHistory.pop();
//                        shape.getMadeShape().groupHistory.push(g);
                        if(!myGroupsList.contains(g)) {
                            g.groupSelected = true;
                            myGroupsList.add(g);
                            selectedGroups.add(g);
                        }
//                        else{
//                            if(!myShapeList.contains(shape)){
//                                shape.getMadeShape().shapeSelected = true;
//                                selectedShapeList.add(shape);
//                                myShapeList.add(shape);
//                            }
//                        }
                    }
                    else{
                        if(!myShapeList.contains(shape)){
                            shape.getMadeShape().shapeSelected = true;
                            selectedShapeList.add(shape);
                            myShapeList.add(shape);
                        }
                    }
                }

            }
            groupSelection.add(ungroupSelection); // use for undo

            // find previous group(s) and add back to group list

//            if (!originalGroup.isEmpty()) {
////                ArrayList<Group> groups = new ArrayList<>();
////                Group og = originalGroup.get(originalGroup.indexOf());
////                groups.add(og);
//                ArrayList<Group> groups = originalGroup.remove(originalGroup.size() - 1);
//                redoGroups.add(groups);
//                for (Group g : groups) {
//                    g.groupSelected = true;
//                    myGroupsList.add(g);
//                    selectedGroups.add(g);
//                }
//            }
        }
        drawMyShapes();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if(!groupSelection.isEmpty()){
            ArrayList<Group> reGroup = groupSelection.remove(groupSelection.size()-1);
            ungroupSelection.add(reGroup);

            for(Group group : reGroup){
                group.groupSelected = true;
                myGroupsList.add(group);
                selectedGroups.add(group);
            }
        }
        if(!redoGroups.isEmpty()){
            ArrayList<Group> groups = redoGroups.remove(redoGroups.size() - 1);
            originalGroup.add(groups);
            for (Group g : groups) {
//                g.groupSelected = true;
                myGroupsList.remove(g);
                selectedGroups.remove(g);
            }
        }


//        if(!undoGroup.isEmpty()) {
//            for (Group g : undoGroup) {
//                g.groupSelected = true;
//                myGroupsList.add(g);
//                selectedGroups.add(g);
//                redoGroup.add(g);
//
//                for(IShape s : g.getGroupedShapes()) {
//                   s.getMadeShape().increaseGroup();
//                   myShapeList.remove(s);
//                   selectedShapeList.remove(s);
//                }
//            }
//        }
        drawMyShapes();
        // 1 level of re-grouping
    }

    @Override
    public void redo() {
        if(!redoGroup.isEmpty()) {
            for (Group g : redoGroup) {
                myGroupsList.remove(g);
                selectedGroups.remove(g);
                undoGroup.add(g);
                for(IShape s: g.getGroupedShapes()){
                    s.getMadeShape().decreaseGroup();
                    if(s.getMadeShape().numberOfGroups<1){
                        s.getMadeShape().shapeSelected = true;
                        myShapeList.add(s);
                        selectedShapeList.add(s);
                    }
                }
            }
        }
        drawMyShapes();
    }
}
