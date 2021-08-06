package view.gui;

import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class PaintCanvas extends PaintCanvasBase {
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }



    @Override
    /**
     * This is an event handler.  If this function gets called, its time to
     * draw the entire picture.
     * It you want to force a paint event, call aPaintCanvas.repaint()
     */
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Time to repaint");
    }

}
