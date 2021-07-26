package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShape;

import java.awt.*;

public class Ellipse implements IShape {
    MakeShape makeShape;
    private Color color;
    private Color secondaryColor;

    Ellipse(MakeShape makeShape){
        this.makeShape = makeShape;
    }

    @Override
    public void draw(Graphics g2d) {
        Graphics2D g = (Graphics2D) g2d;
        color = makeShape.shapeConfigs.getPrimaryColor();
        secondaryColor = makeShape.shapeConfigs.getSecondaryColor();
        int x = (int) makeShape.getMin().getX();
        int y = (int) makeShape.getMin().getY();
        int w = makeShape.getWidth();
        int h = makeShape.getHeight();


        if (makeShape.shapeConfigs.getShadingType().equals(ShapeShadingType.OUTLINE)){
            g.setColor(color);
            g.setStroke(new BasicStroke(5));
            g.drawOval(x, y, w, h);
        }
        else if(makeShape.shapeConfigs.getShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(color);
            g.drawOval(x, y, w, h);
            g.fillOval(x, y, w, h);
        }
        else{
            g.setStroke(new BasicStroke(5));
            g.setColor(secondaryColor);
            g.fillOval(x, y, w, h);
            g.setColor(color);
            g.drawOval(x, y, w, h);
        }
//        System.out.println("drawing ellipse in Ellipse");
    }

    @Override
    public MakeShape getMadeShape() {
        return makeShape;
    }

}
