package controller;


public class DoUndo implements ICommand{
    //DrawShapes drawShapes;
    //private MyShapesList myShapesList = new MyShapesList(drawShapes);
    //public ArrayList<IShape> iShapeArrayList = new ArrayList<IShape>();
//    private ApplicationState applicationState;


    @Override
    public void run() {
//        System.out.println("in DoUndo");
        CommandHistory.undo();
    }


}
