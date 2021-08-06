package controller;

import model.interfaces.IShape;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;

public class DeleteShapeCommand implements ICommand, IUndoable{
    DeleteShapeCommand() { }

    @Override
    public void run() {
        if(selectedShapeList.size() == 0){
            System.out.println("Nothing to delete");
            return;
        }
        // make new array list in case multiple shapes are selected
        ArrayList<IShape> d = new ArrayList<>();
        for(IShape delete : selectedShapeList){
            delete.getMadeShape().deleted = true;
            d.add(delete);
        }
        myShapeList.removeAll(selectedShapeList);
        deletedSelection.add(d);
        selectedShapeList.clear();
        drawMyShapes(); // redraw with updated shape list
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // adds shape(s) back into shape list
        if(!deletedSelection.isEmpty()){
            ArrayList<IShape> undoDelete = deletedSelection.remove(deletedSelection.size() - 1);
            for (IShape undo : undoDelete){
                undo.getMadeShape().deleted = false;
                myShapeList.add(undo);
            }
            deletedRedoSelection.add(undoDelete);
            drawMyShapes();
        }
    }

    @Override
    public void redo() {
        // removed shape(s) from shape list again
        if(!deletedRedoSelection.isEmpty()) {
            ArrayList<IShape> redoDelete = deletedRedoSelection.remove(deletedRedoSelection.size() - 1);
            for (IShape redo : redoDelete) {
                redo.getMadeShape().deleted = true;
                myShapeList.remove(redo);
            }
            deletedSelection.add(redoDelete);
            drawMyShapes();
        }
    }
}
