package controller;

public class DoRedo implements ICommand{
//    private MyShapesList myShapesList;

    @Override
    public void run() {
//        System.out.println("in DoRedo");
        CommandHistory.redo();

    }
}
