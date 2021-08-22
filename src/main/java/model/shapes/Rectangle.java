package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShape;
import java.awt.*;

import static model.shapes.MyShapesList.groupsList;
import static model.shapes.MyShapesList.myGroupsList;

public class Rectangle implements IShape {
    private Color color;
    private Color secondaryColor;
    MakeShape makeShape;

    // rectangle object
    Rectangle(MakeShape makeShape) {
        this.makeShape = makeShape;
    }

    // draw, fill, stroke, color
    @Override
    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        int h = makeShape.getHeight();
        int w = makeShape.getWidth();
        int x = (int) makeShape.getMin().getX();
        int y = (int) makeShape.getMin().getY();

        color = makeShape.primaryColor.getColor();
        secondaryColor = makeShape.secondaryColor.getColor();

        // outlined
        if(makeShape.shadingType.equals(ShapeShadingType.OUTLINE)){
            g.setColor(color);
            g.setStroke(new BasicStroke(5));
            g.drawRect(x, y, w, h);
        }
        // filled in
        else if(makeShape.shadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(color);
            g.drawRect(x, y, w, h);
            g.fillRect(x, y, w, h);
        }
        // outlined and filled in
        else{
            g.setStroke(new BasicStroke(5));
            g.setColor(color);
            g.fillRect(x, y, w, h);
            g.setColor(secondaryColor);
            g.drawRect(x, y, w, h);
        }
    }

    @Override
    public MakeShape getMadeShape() {
        return makeShape;
    }


    @Override
    public Boolean isGroup() {
        if(makeShape.groupsList.isEmpty()) {
            return false;
        }
        return true;
    }

}
