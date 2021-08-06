package controller;

public class DoRedo implements ICommand{
    @Override
    public void run() { CommandHistory.redo(); }
}
