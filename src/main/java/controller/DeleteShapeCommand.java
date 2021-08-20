package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;

public class DeleteShapeCommand implements ICommand, IUndoable{
    private ArrayList<ArrayList<Group>> deletedGroupList = new ArrayList<>();
    private ArrayList<ArrayList<Group>> redoDeletedGroup = new ArrayList<>();
    private ArrayList<ArrayList<IShape>> deletedSelection = new ArrayList<>();
    private ArrayList<ArrayList<IShape>> deletedRedoSelection = new ArrayList<>();

    @Override
    public void run() {
        if(selectedShapeList.size() == 0 && selectedGroups.size() ==0){
            System.out.println("Nothing to delete");
            return;
        }
        // make new array list in case multiple shapes are selected
        ArrayList<IShape> d = new ArrayList<>();
        for(IShape delete : selectedShapeList){
            d.add(delete);
        }
        myShapeList.removeAll(selectedShapeList);
        deletedSelection.add(d);
        selectedShapeList.clear();

        ArrayList<Group> dG = new ArrayList<>();
        for(Group g: selectedGroups){ dG.add(g); }
//        for(Group group : dG){
//            for(IShape shape : group.getGroupedShapes()){
//                myShapeList.remove(shape);
//            }
//        }
        myGroupsList.removeAll(selectedGroups);
        deletedGroupList.add(dG);
        selectedGroups.clear();

        drawMyShapes(); // redraw with updated shape list
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // adds shape(s) back into shape list
        if(!deletedSelection.isEmpty()){
            ArrayList<IShape> undoDelete = deletedSelection.remove(deletedSelection.size() - 1);
            for (IShape undo : undoDelete){
                undo.getMadeShape().shapeSelected = true;
                myShapeList.add(undo);
                selectedShapeList.add(undo);
            }
            deletedRedoSelection.add(undoDelete);
        }

        if(!deletedGroupList.isEmpty()){
            ArrayList<Group> undoGroupD = deletedGroupList.remove(deletedGroupList.size()-1);
            for(Group undo : undoGroupD){
                undo.groupSelected = true;
                myGroupsList.add(undo);
                selectedGroups.add(undo);
//                for(IShape shape : undo.getGroupedShapes()){ myShapeList.add(shape); }
            }
            redoDeletedGroup.add(undoGroupD);
        }
        drawMyShapes();
    }

    @Override
    public void redo() {
        // removed shape(s) from shape list again
        if(!deletedRedoSelection.isEmpty()) {
            ArrayList<IShape> redoDelete = deletedRedoSelection.remove(deletedRedoSelection.size() - 1);
            for (IShape redo : redoDelete) {
                myShapeList.remove(redo);
                selectedShapeList.remove(redo);
            }
            deletedSelection.add(redoDelete);
        }

        if(!redoDeletedGroup.isEmpty()){
            ArrayList<Group> redoGroup = redoDeletedGroup.remove(redoDeletedGroup.size()-1);
            for(Group g : redoGroup){
                myGroupsList.remove(g);
                selectedGroups.remove(g);
//                for(IShape shape : g.getGroupedShapes()){ myShapeList.remove(shape); }
            }
            deletedGroupList.add(redoGroup);
        }
        drawMyShapes();
    }
}
