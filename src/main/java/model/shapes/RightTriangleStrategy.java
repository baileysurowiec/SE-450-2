package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShape;
import java.awt.*;

public class RightTriangleStrategy implements TrianglePointsStrategy{
    private Color color;
    private Color secondaryColor;

    @Override
    public void drawTriangle(MakeShape makeShape, Graphics2D g) {
        color = makeShape.primaryColor.getColor();
        secondaryColor = makeShape.secondaryColor.getColor();

//        System.out.println(color.toString());
        int sX = (int) makeShape.getStartC().getX();
        int eX = (int) makeShape.getEndC().getX();
        int sY = (int) makeShape.getStartC().getY();
        int eY = (int) makeShape.getEndC().getY();
        int nX = sX;
        int nY = eY;

        if(makeShape.shadingType.equals(ShapeShadingType.OUTLINE)){
            g.setStroke(new BasicStroke(5));
            g.setColor(color);
            g.drawPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
        }
        // filled in
        else if(makeShape.shadingType.equals(ShapeShadingType.FILLED_IN)){
            g.drawPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
            g.fillPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
            g.setColor(color);
        }
        // outline and filled in
        else{
            g.setStroke(new BasicStroke(5));
            g.setColor(color);
            g.fillPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
            g.setColor(secondaryColor);
            g.drawPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
        }
    }
    @Override
    public void drawSelectedTriangle(IShape shape, Graphics2D g) {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setColor(Color.RED);
        g.setStroke(stroke);
        int sX = (int) shape.getMadeShape().getStartC().getX();
        int eX = (int) shape.getMadeShape().getEndC().getX();
        int sY = (int) shape.getMadeShape().getStartC().getY();
        int eY = (int) shape.getMadeShape().getEndC().getY();

        if (sX > eX && sY < eY){
            sX += 5;
            sY -= 10;
            eX -= 10;
            eY += 5;
        }
        else{
            eX += 10;
            eY -= 5;
            sX -= 5;
            sY += 10;
        }
        int nX = sX;
        int nY = eY;
        g.drawPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
    }
}
