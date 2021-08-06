package controller.state;

import controller.ICommand;
import controller.MoveShapesCommand;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import java.awt.*;

public class MoveState implements IState {
    ICommand command;
    @Override
    public void doState(ApplicationState appState, Point startC, Point endC, MyShapesList myShapesList) {
        if(myShapesList.getMyShapeList().isEmpty()){
            System.out.println("Nothing to move");
            return;
        }
        command = new MoveShapesCommand(startC, endC, myShapesList);
        command.run();
    }
}
