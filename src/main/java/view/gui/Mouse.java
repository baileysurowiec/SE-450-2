package view.gui;

import controller.*;
import model.MouseMode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.CreateShapesCommand;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import view.interfaces.PaintCanvasBase;


public class Mouse extends MouseAdapter {
    private Point startC;
    private Point endC;
    ApplicationState applicationState;
    private MyShapesList myShapesList;  // this is my list of shapes that gets painted
    public PaintCanvasBase pcb;


    // responsive mouse object
    // app state needs to know about mouse listener pass these into mouse object:
    // add a mouse listener to the new paint canvas from Main, pass new vars into mouse to run
    public Mouse(ApplicationState applicationState, MyShapesList myShapesList, PaintCanvasBase pcb){
        this.applicationState = applicationState;
        this.myShapesList = myShapesList;
        this.pcb = pcb;
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("mouse click");
        startC = new Point(e.getX(), e.getY()); // sets on every mouse press
    }

    // mouse handler creates instance of Create Shape on mouse released event
    @Override
    public void mouseReleased(MouseEvent e){
//        System.out.println("mouse release");
        endC = new Point(e.getX(), e.getY());

        if (applicationState.getActiveMouseMode().equals(MouseMode.DRAW)) {
            myShapesList.clearSelected(); // start with an empty list
            CreateShapesCommand newShape = new CreateShapesCommand(applicationState, startC, endC, myShapesList);
            newShape.run();
        }
        else if(applicationState.getActiveMouseMode().equals(MouseMode.MOVE)){
//            System.out.println("in move mouse mode");
            MoveShapesCommand newMove = new MoveShapesCommand(applicationState, startC, endC, myShapesList);
            newMove.run();
        }
        else if(applicationState.getActiveMouseMode().equals(MouseMode.SELECT)){
            SelectShapesCommand newSelect = new SelectShapesCommand(myShapesList, startC, endC, applicationState);
            newSelect.run();
//            System.out.println("in select mouse mode");
        }
        else{
            System.out.println("choose valid mouse mode");
        }
    }


}
