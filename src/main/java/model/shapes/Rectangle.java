package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShape;

import java.awt.*;

public class Rectangle implements IShape {

    private Color color;
    private Color secondaryColor;
    MakeShape makeShape;
//    private boolean isSelected;

    // rectangle object
    Rectangle(MakeShape makeShape) {
        this.makeShape = makeShape;
    }

    // draw, fill, stroke, color
    @Override
    public void draw(Graphics g2d) {
        Graphics2D g = (Graphics2D) g2d;
        int h = makeShape.getHeight();
        int w = makeShape.getWidth();
        int x = (int) makeShape.getMin().getX();
        int y = (int) makeShape.getMin().getY();

        color = makeShape.shapeConfigs.getPrimaryColor();
        secondaryColor = makeShape.shapeConfigs.getSecondaryColor();

        // outlined
        if (makeShape.shapeConfigs.getShadingType().equals(ShapeShadingType.OUTLINE)){
            g.setColor(color);
            g.setStroke(new BasicStroke(5));
            g.drawRect(x, y, w, h);

        }
        // filled in
        else if(makeShape.shapeConfigs.getShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(color);
            g.drawRect(x, y, w, h);
            g.fillRect(x, y, w, h);
        }
        // outlined and filled in
        else{
            g.setStroke(new BasicStroke(5));
            g.setColor(secondaryColor);
            g.fillRect(x, y, w, h);
            g.setColor(color);
            g.drawRect(x, y, w, h);
        }
//        System.out.println("drawing rectangle in Rectangle");
    }

    @Override
    public MakeShape getMadeShape() {
        return makeShape;
    }

}
