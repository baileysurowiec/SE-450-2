package controller.state;

import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import java.awt.*;

public interface IState {
    void doState(ApplicationState appState, Point start, Point end, MyShapesList myShapesList);
}
