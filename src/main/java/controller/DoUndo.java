package controller;

public class DoUndo implements ICommand{
    @Override
    public void run() { CommandHistory.undo(); }
}
