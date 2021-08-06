package controller.state;

import controller.ICommand;
import controller.SelectShapesCommand;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import java.awt.*;

public class SelectState implements IState {
    ICommand command;
    @Override
    public void doState(ApplicationState appState, Point startC, Point endC, MyShapesList myShapesList) {
        if(myShapesList.getMyShapeList().isEmpty()) {
            System.out.println("Nothing to select");
            return;
        }
        command = new SelectShapesCommand(myShapesList, startC, endC);
        command.run();
    }
}
