package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShape;
import java.awt.*;

public class Ellipse implements IShape {
    MakeShape makeShape;
    private Color color;
    private Color secondaryColor;
    private Boolean grouped = false;

    Ellipse(MakeShape makeShape){
        this.makeShape = makeShape;
    }


    @Override
    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        color = makeShape.primaryColor.getColor();
        secondaryColor = makeShape.secondaryColor.getColor();
        int x = (int) makeShape.getMin().getX();
        int y = (int) makeShape.getMin().getY();
        int w = makeShape.getWidth();
        int h = makeShape.getHeight();


        if(makeShape.shadingType.equals(ShapeShadingType.OUTLINE)){
            g.setColor(color);
            g.setStroke(new BasicStroke(5));
            g.drawOval(x, y, w, h);
        }
        else if(makeShape.shadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(color);
            g.drawOval(x, y, w, h);
            g.fillOval(x, y, w, h);
        }
        else{
            g.setStroke(new BasicStroke(5));
            g.setColor(color);
            g.fillOval(x, y, w, h);
            g.setColor(secondaryColor);
            g.drawOval(x, y, w, h);
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
