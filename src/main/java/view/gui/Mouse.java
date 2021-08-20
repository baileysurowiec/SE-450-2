package view.gui;

import controller.ICommand;
import controller.state.DrawState;
import controller.state.IState;
import controller.state.MoveState;
import controller.state.SelectState;
import model.MouseMode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.persistence.ApplicationState;
import model.shapes.MyShapesList;
import view.interfaces.PaintCanvasBase;

public class Mouse extends MouseAdapter {
    private Point startC;
    private Point endC;
    private ApplicationState applicationState;
    private MyShapesList myShapesList;  // this is my list of shapes that gets painted
    public PaintCanvasBase pcb;
    private IState state;
    private ICommand command;

    // responsive mouse object
    // app state needs to know about mouse listener pass these into mouse object:
    // add a mouse listener to the new paint canvas from Main, pass new vars into mouse to run
    public Mouse(ApplicationState applicationState, MyShapesList myShapesList, PaintCanvasBase pcb){
        this.applicationState = applicationState;
        this.myShapesList = myShapesList;
        this.pcb = pcb;
    }

    public void setState(){
        if (applicationState.getActiveMouseMode().equals(MouseMode.DRAW)) {
            state = new DrawState();
        }
        else if(applicationState.getActiveMouseMode().equals(MouseMode.MOVE)){
            state = new MoveState();
        }
        else{ state = new SelectState(); }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startC = new Point(e.getX(), e.getY()); // sets on every mouse press
        setState();
    }

    // mouse handler creates instance of Create Shape on mouse released event
    @Override
    public void mouseReleased(MouseEvent e){
        endC = new Point(e.getX(), e.getY());
//        state.setDrawSettings(applicationState);
        state.doState(applicationState, startC, endC, myShapesList);
        command = state.getCommand();
        command.run();
    }

}
