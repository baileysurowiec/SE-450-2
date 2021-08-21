package controller.state;

import controller.ICommand;
import controller.SelectShapesCommand;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import java.awt.*;
import static model.shapes.MyShapesList.myGroupsList;

public class SelectState implements IState {
    ICommand command;
    @Override
    public void doState(ApplicationState applicationState, Point startC, Point endC, MyShapesList myShapesList) {
        if(myShapesList.getMyShapeList().isEmpty() && myGroupsList.isEmpty()) {
            System.out.println("Nothing to select");
            return;
        }
        command = new SelectShapesCommand(myShapesList, startC, endC);
    }

    @Override
    public ICommand getCommand() {
        return command;
    }

}
