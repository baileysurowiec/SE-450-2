package controller;

public class UnGroupShapesCommand implements ICommand, IUndoable{
    @Override
    public void run() {

    }

    @Override
    public void undo() {
        // 1 level of re-grouping

    }

    @Override
    public void redo() {
        // 1 level of ungrouping

    }
}
