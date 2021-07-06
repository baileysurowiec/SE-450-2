package model.shapes;

import model.ShapeShadingType;

import java.awt.*;

public class Rectangle {
   // coordinates from mouse click and release
   // calculate rectangle area using x,y pairs
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    // Graphics object
    private Graphics2D graphics2D;

    // shading and color
    private ShapeShadingType shadingType;
    private Color primaryColor;
    private Color secondaryColor;


    public Rectangle(int x1, int y1, int x2, int y2, Graphics2D graphics2D, ShapeShadingType shadingType, Color primaryColor, Color secondaryColor){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.graphics2D = graphics2D;
        this.shadingType = shadingType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    // methods of Graphics2D
    // draw, fill, stroke, color
    public void drawRect(){
        // find starting corner and WxH of rectangle
        int xL = Math.min(x1, x2);
        int yL = Math.min(y1, y2);
        int w = Math.abs(x1-x2);
        int h = Math.abs(y1-y2);

        // if rectangle is outlined
        if (shadingType == ShapeShadingType.OUTLINE) {
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.setColor(secondaryColor); // this color needs to only be outline color
        }

        // if rectangles is filled
        else if (shadingType == ShapeShadingType.FILLED_IN) {
            graphics2D.fillRect(xL, yL, w, h);
            graphics2D.setColor(primaryColor);
        }
        else if (shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics2D.fillRect(xL,yL, w, h);
            graphics2D.setColor(primaryColor);
            graphics2D.setStroke(new BasicStroke(5));
            // set outline color

            graphics2D.drawRect(xL, yL, w, h); // x, y of upper left hand corner, width and height
        }

    }


}
