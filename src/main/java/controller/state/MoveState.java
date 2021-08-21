package controller.state;

import controller.ICommand;
import controller.MoveShapesCommand;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import java.awt.*;
import static model.shapes.MyShapesList.myGroupsList;

public class MoveState implements IState {
    ICommand command;
    @Override
    public void doState(ApplicationState applicationState, Point startC, Point endC, MyShapesList myShapesList) {
        if(myShapesList.getMyShapeList().isEmpty() && myGroupsList.isEmpty()){
            System.out.println("Nothing to move");
            return;
        }
        command = new MoveShapesCommand(startC, endC, myShapesList);
    }

    @Override
    public ICommand getCommand() {
        return command;
    }
}
