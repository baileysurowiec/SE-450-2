package main;
import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.*;

import model.shapes.MyShapesList;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas(); // add mouse listener to paint canvas
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule); // mouse needs to know about app state
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        // initialize new for mouse
        MyShapesList myShapesList = new MyShapesList(appState, paintCanvas);
            // pass to mouse
        Mouse mouse = new Mouse(appState, myShapesList, paintCanvas);
        paintCanvas.addMouseListener(mouse);

    }
}
