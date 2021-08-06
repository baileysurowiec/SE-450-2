package model.shapes;
import model.interfaces.IShape;

import java.awt.*;

public interface TrianglePointsStrategy {
    void drawTriangle(MakeShape makeShape, Graphics2D g);
    void drawSelectedTriangle(IShape shape, Graphics2D g);
}
