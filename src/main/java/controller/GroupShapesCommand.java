package controller;

public class GroupShapesCommand implements ICommand, IUndoable{
    @Override
    public void run() {

    }

    @Override
    public void undo() {
        // undo 1 level of grouping per undo

    }

    @Override
    public void redo() {
        // redo 1 level of grouping per redo

    }
}
