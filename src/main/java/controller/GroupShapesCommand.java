package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import model.shapes.NullObject;

import java.util.ArrayList;
import static model.shapes.MyShapesList.*;

public class GroupShapesCommand implements ICommand, IUndoable{
    public static ArrayList<Group> redoGroup = new ArrayList<>();

    public static ArrayList<ArrayList<Group>> originalGroup = new ArrayList<>(); // grouped saved for later
    public static ArrayList<ArrayList<Group>> redoGroups = new ArrayList<>();

    private ArrayList<ArrayList<IShape>> shapesForGroups = new ArrayList<>();
    private ArrayList<ArrayList<IShape>> redoShapes = new ArrayList<>();

    private ArrayList<ArrayList<IShape>> redoShapeList = new ArrayList<>();

    private ArrayList<ArrayList<Group>> redo = new ArrayList<>();
    private ArrayList<ArrayList<Group>> storeRedoGroups = new ArrayList<>();

    @Override
    public void run() {
        if(selectedShapeList.size() <= 1 && selectedGroups.size() <1 || selectedShapeList.size() < 1 && selectedGroups.size() <= 1) { return; }
        ArrayList<IShape> shapeList = new ArrayList<>();
        shapeList.addAll(selectedShapeList);
        selectedShapeList.clear();

        ArrayList<IShape> combinedList = new ArrayList<>();
        combinedList.addAll(shapeList);
        myShapeList.removeAll(shapeList);
        for(IShape shape : shapeList){
            shape.getMadeShape().increaseGroup();
//            shape.getMadeShape().shapeSelected = false;
        }

        ArrayList<Group> saveGroups = new ArrayList<>();
        for(Group group: selectedGroups){
            saveGroups.add(group);
            for(IShape shape : group.getGroupedShapes()){
                combinedList.add(shape);
                shape.getMadeShape().increaseGroup();
                shape.getMadeShape().undoGroupHistory.add(group); // add previous group history to stack
            }
            myGroupsList.remove(group); // remove old group from group list
        }
        originalGroup.add(saveGroups); // save for undo


        // new group from selected shapes and grouped shapes
        Group newGroup = new Group(combinedList);
        newGroup.groupSelected = true;
        selectedGroups.add(newGroup);
        myGroupsList.add(newGroup);


//        for(IShape shape: myShapeList){ // for every shape in shapeList, set group to last group in group list
//            if(shape.getMadeShape().group != null) {
////                ArrayList<IShape> soloGroup = new ArrayList<>();
////                soloGroup.add(shape);
////                Group solo = new Group(soloGroup);
//
//                shape.getMadeShape().addToGroupList(shape.getMadeShape().group); // save each shapes current group to list
//            }
//                shape.getMadeShape().setShapeGroup();
//        }


        drawMyShapes();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        undoGroup(myGroupsList);

//        if (!myGroupsList.isEmpty()) {
//            Group group = myGroupsList.remove(myGroupsList.size() - 1); // remove last inserted group from list
//
//            ArrayList<IShape> restoreShapes = new ArrayList<>();
//            ArrayList<Group> prevGroups = new ArrayList<>();
//            for (IShape shape : group.getGroupedShapes()) { // look at every shape in removed group
//                shape.getMadeShape().groupHistory.push(group); // add it to groupHistory for each shape
//                if (!shape.getMadeShape().undoGroupHistory.isEmpty()) { // check shapes group history // might still need??
//                    Group g = shape.getMadeShape().undoGroupHistory.pop(); // pop most recent group off stack
//                    // need to add previous group to groupList
//                    if (!myGroupsList.contains(g)) { // add previous group to group list, if needed
//                        g.groupSelected = true;
////                        groupsList.add(g);
//                        myGroupsList.add(g);
//                        selectedGroups.add(g);
//                        prevGroups.add(g);
//                    }
//                } else {
//                    shape.getMadeShape().shapeSelected = true;
//                    selectedShapeList.add(shape);
//                    restoreShapes.add(shape);
//                }
//                redoShapeList.add(restoreShapes);
//            }
//            storeRedoGroups.add(prevGroups);
//        }
//    }


//    public void undo(){
//        for(IShape s : myShapeList){
//            MakeShape shape = s.getMadeShape();
//            shape.undoGroup();
//            shape.soloShapeCount++;
//        }
//        drawMyShapes();
//
//        // stack below works for undo
////        if(!myGroupsList.isEmpty()) {
////            Group group = myGroupsList.remove(myGroupsList.size() - 1); // remove last inserted group from list
//// try with every group in my shapes list
//        int biggestStack = 0;
//        for (IShape s : myShapeList){
////            for(Group group : s.getMadeShape().groupsList) {
////            ArrayList<IShape> restoreShapes = new ArrayList<>(); // needed?
////            ArrayList<Group> prevGroups = new ArrayList<>();
////                for (IShape shape : group.getGroupedShapes()) { // look at every shape in removed group
//
//            s.getMadeShape().groupHistory.push(s.getMadeShape().group); // add current group to stack
////                    shape.getMadeShape().groupHistory.push(group); // add it to groupHistory for each shape
//                    if (!s.getMadeShape().undoGroupHistory.isEmpty()) { // check shapes group history
//                        int undostacksize = s.getMadeShape().undoGroupHistory.size();
//                        if (biggestStack == 0 || undostacksize > biggestStack) {
//                            biggestStack = undostacksize; // find the largest stack for each shape in shape list
//                        }
//                    }
////                }
////            }
////            Group g = s.getMadeShape().undoGroupHistory.pop(); // pop most recent group off stack
//            if(s.getMadeShape().undoGroupHistory.size() == biggestStack && biggestStack !=0 ){
//                Group g = s.getMadeShape().undoGroupHistory.pop();
//                    // need to add previous group to groupList
//                    if (!myGroupsList.contains(g)) { // add previous group to group list, if needed
//                        g.groupSelected = true;
//                        myGroupsList.add(g);
//                        selectedGroups.add(g);
////                        prevGroups.add(g);
//                    }
//                    else{
//                        s.getMadeShape().shapeSelected = true;
//                        selectedShapeList.add(s);
//                    }
//                }
//                else {
//                    for(Group group : s.getMadeShape().groupsList){
////                        group.groupSelected = false;
//                        myGroupsList.remove(group);
//                    }
////                    s.getMadeShape().shapeSelected = true;
////                    myShapeList.add(shape);
////                    selectedShapeList.add(s);
////                    restoreShapes.add(shape);
//
//                }
////                redoShapeList.add(restoreShapes);
//            }
////            storeRedoGroups.add(prevGroups);
////        }}
//            drawMyShapes();
//
//
//
//
//
//            // everything below works
////            // remove group from groups list
//            Group group = myGroupsList.remove(myGroupsList.size() - 1);
//            selectedGroups.remove(group);
//
//            for(IShape shape: group.getGroupedShapes()) {
//                if (!shape.getMadeShape().groupHistory.isEmpty()) {
//                    shape.getMadeShape().undoGroupHistory.push(shape.getMadeShape().groupHistory.pop());
//                }
//            }
//            // save group for a redo
//            redoGroup.add(group);
//            // find previous group(s) and add back to group list
//            if (!originalGroup.isEmpty()) {
//                ArrayList<Group> groups = originalGroup.remove(originalGroup.size() - 1);
//                redoGroups.add(groups);
//                for (Group g : groups) {
//                    g.groupSelected = true;
//                    myGroupsList.add(g);
//                    selectedGroups.add(g);
//                }
//            }
//        }
        drawMyShapes();
//
    }




    public void undoGroup(ArrayList<Group> groupList){
        if(!groupList.isEmpty()) {
            // remove group from groups list
            Group group = groupList.remove(groupList.size() - 1);
            selectedGroups.remove(group);
            // save group for a redo
            redoGroup.add(group);

            ArrayList<IShape> soloShapes = new ArrayList<>();
            for (IShape s : group.getGroupedShapes()) {
                MakeShape shape = s.getMadeShape();
                shape.decreaseGroup();
                if(!shape.undoGroupHistory.isEmpty()){
                    shape.groupHistory.push(shape.undoGroupHistory.pop());
                }

                if (shape.numberOfGroups == 0 ) {
                    shape.shapeSelected = true;
                    myShapeList.add(s);
                    selectedShapeList.add(s);
                    soloShapes.add(s);
                }
            }
            redoShapeList.add(soloShapes);

            // find previous group(s) and add back to group list
            if (!originalGroup.isEmpty()) {
                ArrayList<Group> groups = originalGroup.remove(originalGroup.size() - 1); // get list of other groups
                redoGroups.add(groups); // save list for redoing a group
                for (Group g : groups) {
//                    for(IShape shape : g.groupedShapes){
//                        shape.getMadeShape().decreaseGroup();
//                    }
                    g.groupSelected = true;
                    myGroupsList.add(g);
                    selectedGroups.add(g);
                }
            }
        }
    }

    @Override
    public void redo(){
//        for(IShape s : myShapeList){
//            MakeShape shape = s.getMadeShape();
//            shape.redoGroup();
//            shape.soloShapeCount--;
//        }
//        drawMyShapes();
// stack
//        if(!storeRedoGroups.isEmpty()) {
//            ArrayList<Group> redoGroups = storeRedoGroups.remove(storeRedoGroups.size()-1);
//            for(Group group : redoGroups) {
//                myGroupsList.remove(group);
//                for (IShape shape : group.getGroupedShapes()) { // look at every shape in removed group
//                    shape.getMadeShape().undoGroupHistory.push(group); // add it to undoGroupHistory for each shape
//
//                    if (!shape.getMadeShape().groupHistory.isEmpty()) { // check shapes group history
//                        Group g = shape.getMadeShape().groupHistory.pop(); // pop most recent group off stack
//
//                        if (!myGroupsList.contains(g)) { // add previous group to group list, if needed
//                            g.groupSelected = true;
//                            myGroupsList.add(g);
//                            selectedGroups.add(g);
//                        } // else {
////                            shape.getMadeShape().shapeSelected = true;
////                            selectedShapeList.add(shape);
////                        }
//                    } else {
//                        shape.getMadeShape().shapeSelected = true;
//                        selectedShapeList.add(shape);
//                    }
//                }
//            }
//        }


// everything below works
        // remove groups from grouplist
        if(!redoGroups.isEmpty()){
            ArrayList<Group> groups = redoGroups.remove(redoGroups.size()-1);
            originalGroup.add(groups);
            for(Group g: groups){
                myGroupsList.remove(g);
                selectedGroups.remove(g);
            }
        }
        // find previous group
        if(!redoGroup.isEmpty()) {
            Group group = redoGroup.remove(redoGroup.size() - 1);
            group.groupSelected = true;
            myGroupsList.add(group);
            selectedGroups.add(group);

//            ArrayList<IShape> soloShapes = new ArrayList<>();
            for(IShape s: group.getGroupedShapes()) {
                MakeShape shape = s.getMadeShape();
                shape.increaseGroup();
                if (!shape.groupHistory.isEmpty()) {
                    shape.undoGroupHistory.push(shape.groupHistory.pop());
                }
                if (shape.numberOfGroups > 0 ) {
                    myShapeList.remove(s);
                    selectedShapeList.remove(s);
//                    soloShapes.add(s);
                }
            }

//            if(!redoShapeList.isEmpty()) {
//                ArrayList<IShape> shapes = redoShapeList.remove(redoShapeList.size() - 1);
//                for (IShape s : shapes) {
//                    // remove single shapes from shape list
////                    s.getMadeShape().increaseGroup();
//                    if (s.getMadeShape().numberOfGroups > 1) {
//                        myShapeList.remove(s);
//                        selectedShapeList.remove(s);
//                    }
//                }
//            }
        }
//        setGroupsList();
        drawMyShapes();
    }
}
