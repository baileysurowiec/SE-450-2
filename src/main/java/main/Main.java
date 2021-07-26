package main;

//import controller.DrawShapes;
import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.*;

import model.shapes.MyShapesList;
import model.shapes.ShapeConfigs;
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


        // For example purposes only; remove all lines below from your final project.
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Graphics2D graphics2D = paintCanvas.getGraphics2D();
//      // Filled in rectangle
//        Graphics2D graphics2d = paintCanvas.getGraphics2D();
//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//      // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//      // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);
        // end of example
    }
}
