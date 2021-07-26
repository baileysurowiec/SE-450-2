package model.shapes;

import model.ShapeShadingType;

import java.awt.*;

public class RightTriangleStrategy implements TrianglePointsStrategy{
    private Color color;
    private Color secondaryColor;

    @Override
    public void drawTriangle(MakeShape makeShape, Graphics2D g) {
        color = makeShape.shapeConfigs.getPrimaryColor();
        secondaryColor = makeShape.shapeConfigs.getSecondaryColor();

        int sX = (int) makeShape.getStartC().getX();
        int eX = (int) makeShape.getEndC().getX();
        int nX = sX;
        int sY = (int) makeShape.getStartC().getY();
        int eY = (int) makeShape.getEndC().getY();
        int nY = eY;

        if (makeShape.shapeConfigs.getShadingType().equals(ShapeShadingType.OUTLINE)){
            g.setStroke(new BasicStroke(5));
            g.setColor(color);
            g.drawPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
        }
        // filled in
        else if (makeShape.shapeConfigs.getShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.drawPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
            g.fillPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
            g.setColor(color);
        }
        // outline and filled in
        else{
            g.setStroke(new BasicStroke(5));
            g.setColor(secondaryColor);
            g.fillPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
            g.setColor(color);
            g.drawPolygon(new int[]{sX, eX, nX}, new int[]{sY, eY, nY}, 3);
        }
    }
}
