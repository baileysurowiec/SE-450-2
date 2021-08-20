package controller.state;

import controller.ICommand;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import java.awt.*;

public interface IState {
    void doState(ApplicationState applicationState, Point start, Point end, MyShapesList myShapesList);
    ICommand getCommand();
//    void setDrawSettings(ApplicationState applicationState);
}
