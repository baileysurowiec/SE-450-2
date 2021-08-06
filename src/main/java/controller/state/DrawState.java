package controller.state;

import controller.CreateShapesCommand;
import controller.ICommand;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import java.awt.*;

public class DrawState implements IState {
    ICommand command;
    @Override
    public void doState(ApplicationState applicationState, Point start, Point end, MyShapesList myShapesList) {
        Point startC = start;
        Point endC = end;
        ShapeType shapeType = applicationState.getActiveShapeType();
        ShapeShadingType shadingType = applicationState.getActiveShapeShadingType();
        ShapeColor primaryColor = applicationState.getActivePrimaryColor();
        ShapeColor secondaryColor = applicationState.getActiveSecondaryColor();
        myShapesList.clearSelected(); // start with an empty selected list

        command = new CreateShapesCommand(shapeType, shadingType, primaryColor, secondaryColor, startC, endC, myShapesList);
        command.run();
    }
}
